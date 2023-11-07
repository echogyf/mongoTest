package com.gyf.mongotest.config;

import com.gyf.mongotest.entry.Performance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.IndexOperations;

@Configuration
public class MongoDBConfig {

    @Bean
    public IndexOperations indexOps(MongoTemplate mongoTemplate) {
        return mongoTemplate.indexOps(Performance.class);
    }
}