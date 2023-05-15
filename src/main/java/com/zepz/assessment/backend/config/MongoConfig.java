package com.zepz.assessment.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.zepz.assessment.backend.persistence.repository")
public class MongoConfig {
}
