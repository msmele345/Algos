package com.algos.mitch.mongodb

import com.algos.mitch.algo_store.AlgorithmDomainModel

interface AlgorithmMongoRepository : org.springframework.data.mongodb.repository.MongoRepository<AlgorithmDomainModel, String>