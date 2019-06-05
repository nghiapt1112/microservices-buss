package com.nghia.base.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import java.util.Arrays;
import java.util.Objects;

@Configuration
@EnableAutoConfiguration
@Import(value = {MongoDataAutoConfiguration.class})
public class MongoConfiguration extends AbstractMongoConfiguration {

    private static final String TEST = "test";
    private static final String DEFAULT = "default";

    @Autowired
    private MongoProperties properties;

    private String activeProfile;

    public MongoConfiguration(Environment env) {
        if (Arrays.stream(env.getActiveProfiles())
                .anyMatch(el -> el.equalsIgnoreCase(TEST))) {
            this.activeProfile = TEST;
        } else {
            this.activeProfile = DEFAULT;
        }
    }

    public MongoDbFactory defaultMongoDbFactory() {
        if (activeProfile.equalsIgnoreCase(TEST)) {
            return new SimpleMongoDbFactory(mongoClient(), properties.getAuthenticationDatabase());
        } else {
            return new SimpleMongoDbFactory(mongoClient(), properties.getDatabase());
        }
    }

    @Bean
    public MongoClient mongoClient() {
        final int availableCores = Runtime.getRuntime().availableProcessors();
        MongoCredential credentials = null;

        if (activeProfile.equalsIgnoreCase(TEST)) {
            return new MongoClient(
                    new ServerAddress(properties.getHost(), properties.getPort()),
                    new MongoClientOptions
                            .Builder()
                            .connectionsPerHost((Runtime.getRuntime().availableProcessors() * 3 * 3))
                            .build()
            );
        }
        if (properties.getUsername() != null || properties.getPassword() != null) {
            credentials = MongoCredential.createCredential(
                    properties.getUsername(), properties.getAuthenticationDatabase(), properties.getPassword());
        }

        return new MongoClient(
                new ServerAddress(properties.getHost(), properties.getPort()),
                credentials,
                new MongoClientOptions
                        .Builder()
                        .connectionsPerHost((availableCores * 3 * 3))
                        .build()
        );

    }

    @Override
    protected String getDatabaseName() {
        if (activeProfile.equalsIgnoreCase(TEST)) {
            return properties.getAuthenticationDatabase();
        } else {
//             default
            return properties.getDatabase();
        }
    }

    /**
     * Disable _class column in Database
     */
    @Override
    public MappingMongoConverter mappingMongoConverter() throws Exception {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(defaultMongoDbFactory());
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext());
        converter.setCustomConversions(customConversions());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }
}