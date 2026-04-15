package com.sattva.sattva_backend.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        String uri = "mongodb+srv://sattva_admin:Sattva2025@sattva-admin.zfskhqr.mongodb.net/sattva_db?retryWrites=true&w=majority";
        return MongoClients.create(uri);
    }
}
