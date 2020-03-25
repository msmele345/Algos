package com.algos.mitch.services

import com.algos.mitch.algo_store.AlgorithmDomainModel
import com.algos.mitch.algo_store.AlgorithmErrorMapper
import com.algos.mitch.algo_store.AlgorithmRequest
import com.algos.mitch.algo_store.AlgorithmResponse
import com.algos.mitch.mongodb.MongoClient
import com.algos.mitch.redisClient.RedisClient
import com.algos.mitch.result.*
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import javax.xml.ws.Response


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

    fun addAlgorithm(newAlgorithm: AlgorithmDomainModel): ResponseEntity<*> {
        return mongoClient.createAlgorithm(newAlgorithm)
            .mapSuccess { dbResponse ->
                ResponseEntity.status(HttpStatus.OK)
                    .body(dbResponse)
            }.getOrElse { serviceErrors ->
                errorMapper.mapErrors(serviceErrors)
            }
    }

    fun processAllSets(): ResponseEntity<*> {
        return try {
            mongoClient.fetchAllSets().let { sets ->
                ResponseEntity.ok().body(sets)
            }
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.localizedMessage)
        }
    }

    fun findSetById(id: String): ResponseEntity<*> {
        return mongoClient.fetchSetById(id)
            .mapSuccess {
                ResponseEntity.ok(it)
            }.getOrElse { serviceErrors ->
                errorMapper.mapErrors(serviceErrors)
            }
    }
}
