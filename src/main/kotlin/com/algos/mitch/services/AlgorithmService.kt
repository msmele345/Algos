package com.algos.mitch.services

import com.algos.mitch.algo_store.AlgorithmErrorMapper
import com.algos.mitch.algo_store.AlgorithmRequest
import com.algos.mitch.algo_store.AlgorithmResponse
import com.algos.mitch.redisClient.RedisClient
import com.algos.mitch.result.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
class AlgorithmService(
    private val redisClient: RedisClient,
    private val errorMapper: AlgorithmErrorMapper
) {

    fun processAllAlgorithms(): Iterable<AlgorithmResponse> {
        return redisClient.findAllAlgos()

    }

    fun findAlgorithmByName(algorithmRequest: AlgorithmRequest): ResponseEntity<*> {
        return redisClient.findAlgoByName(algorithmRequest.nameId)
            .mapSuccess { algorithmResponse: AlgorithmResponse? ->

                algorithmResponse?.let { validAlgorithmResponse ->
                    ResponseEntity.status(HttpStatus.OK).body(validAlgorithmResponse)
                } ?:
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("Algorithm Not Found")

            }.getOrElse { serviceErrors ->
                errorMapper.mapErrors(serviceErrors)
            }
    }
}
