package com.algos.mitch.redisClient

import com.algos.mitch.algorithms.AlgorithmResponse
import com.algos.mitch.redis.CacheRepository
import org.springframework.stereotype.Service


@Service
class RedisClient(
        private val cacheRepository: CacheRepository
) {

    fun findAllAlgos(): Iterable<AlgorithmResponse> {
        return cacheRepository.findAll()
    }


}