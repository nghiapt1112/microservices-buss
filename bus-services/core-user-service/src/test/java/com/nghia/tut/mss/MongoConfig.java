package com.nghia.tut.mss;

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

    private static final String MONGO_HOST = "localhost";
    private static final int MONGO_PORT = 27017;
    private final int THREAD_PER_CORE = 3;
    private final int MONGO_CONNECTIONS_PER_THREAD = 3;

    @Autowired
    Environment env;

    @Override
    protected String getDatabaseName() {
        return "myDB_TEST";
    }

    @Override
    public MongoClient mongoClient() {
        final int availableCores = Runtime.getRuntime().availableProcessors();
        return new MongoClient(
                new ServerAddress(MONGO_HOST, MONGO_PORT),
                new MongoClientOptions.Builder()
                        .connectionsPerHost((availableCores * THREAD_PER_CORE * MONGO_CONNECTIONS_PER_THREAD)).build());
    }

    @Override
    public MappingMongoConverter mappingMongoConverter() throws Exception {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory());
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext());
        converter.setCustomConversions(customConversions());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }

}
