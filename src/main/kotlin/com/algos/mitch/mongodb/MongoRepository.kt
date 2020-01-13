package com.algos.mitch.mongodb

import com.algos.mitch.algo_store.AlgorithmDomainModel
import org.springframework.data.mongodb.repository.MongoRepository


interface AlgorithmMongoRepository : MongoRepository<AlgorithmDomainModel, String>