package com.algos.mitch.mongodb

import com.mongodb.MongoClient
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoConfiguration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories


@Configuration
@EnableMongoRepositories(basePackages = ["com.algos.*"])
class MongoConfig: AbstractMongoConfiguration() {

    override fun mongoClient(): MongoClient {
        return MongoClient("localhost", 27017)
    }

    override fun getDatabaseName(): String {
        return "algorithmDomainModels"
    }
}