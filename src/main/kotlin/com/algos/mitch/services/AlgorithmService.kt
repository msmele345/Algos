package com.algos.mitch.services

import com.algos.mitch.algorithms.AlgorithmResponse
import com.algos.mitch.redisClient.RedisClient
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ResponseBody


@Service
class AlgorithmService(
    private val redisClient: RedisClient
) {

    @ResponseBody
    fun processAllAlgorithms(): Iterable<AlgorithmResponse> {
        return redisClient.findAllAlgos()

    }

    fun findAlgorithmByName(nameId: String): AlgorithmResponse? {
        return redisClient.findAlgoByName(nameId).let { response ->
            when {
                response.isPresent -> response.get()
                else -> null
            }
        }
    }
}
