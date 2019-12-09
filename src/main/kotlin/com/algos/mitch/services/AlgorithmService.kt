package com.algos.mitch.services

import com.algos.mitch.algo_store.AlgorithmDomainModel
import com.algos.mitch.algo_store.AlgorithmErrorMapper
import com.algos.mitch.algo_store.AlgorithmRequest
import com.algos.mitch.algo_store.AlgorithmResponse
import com.algos.mitch.mongodb.MongoClient
import com.algos.mitch.redisClient.RedisClient
import com.algos.mitch.result.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
class AlgorithmService(
    private val mongoClient: MongoClient,
    private val domainToResponseTransformer: AlgorithmDomainToResponseTransformer,
    private val domainToBatchResponseTransformer: AlgorithmDomainModelToAlgorithmsTransformer,
    private val errorMapper: AlgorithmErrorMapper
) {

    fun processAllAlgorithms(): ResponseEntity<*> {
        return mongoClient.fetchAlgorithms()
            .mapSuccess { algorithms ->
                domainToBatchResponseTransformer.transform(algorithms).let { summaryResponses ->
                    ResponseEntity.status(HttpStatus.OK).body(summaryResponses)
                }
            }.getOrElse { serviceErrors ->
                errorMapper.mapErrors(serviceErrors)
            }
    }

    fun findAlgorithmByName(algorithmRequest: AlgorithmRequest): ResponseEntity<*> {
        return mongoClient.getAlgorithmByName(algorithmRequest.nameId)
            .mapSuccess { algorithmDomainModel: AlgorithmDomainModel? ->

                algorithmDomainModel?.let { validAlgorithmResponse ->
                    ResponseEntity.status(HttpStatus.OK).body(domainToResponseTransformer.transform(validAlgorithmResponse))
                } ?: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Algorithm Not Found")

            }.getOrElse { serviceErrors ->
                errorMapper.mapErrors(serviceErrors)
            }
    }
}
