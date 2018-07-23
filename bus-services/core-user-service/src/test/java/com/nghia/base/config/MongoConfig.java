package com.nghia.base.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

    @Autowired
    Environment env;
    @Value("${mongo.host}")
    private String MONGO_HOST;
    @Value("${mongo.db}")
    private String DB_NAME;

    @Override
    protected String getDatabaseName() {
        return DB_NAME;
    }

    @Override
    public MongoClient mongoClient() {
        final int availableCores = Runtime.getRuntime().availableProcessors();
        return new MongoClient(
                new ServerAddress(MONGO_HOST, this.MONGO_PORT()),
                new MongoClientOptions
                        .Builder()
                        .connectionsPerHost((availableCores * THREAD_PER_CORE() * MONGO_CONNECTIONS_PER_THREAD()))
                        .build()
        );
    }

    @Override
    public MappingMongoConverter mappingMongoConverter() throws Exception {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory());
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext());
        converter.setCustomConversions(customConversions());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }

    private int MONGO_PORT() {
        return env.getProperty("mongo.port", Integer.class);
    }

    private int THREAD_PER_CORE() {
        return env.getProperty("mongo.thread_per_core", Integer.class);
    }

    private int MONGO_CONNECTIONS_PER_THREAD() {
        return env.getProperty("mongo.connection_per_thread", Integer.class);
    }
}
