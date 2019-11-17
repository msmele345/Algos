package com.algos.mitch.redisClient

import com.algos.mitch.algo_store.AlgorithmResponse
import com.algos.mitch.services.AlgorithmDbFaultResolver
import com.algos.mitch.redis.CacheRepository
import com.algos.mitch.result.*
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service


@Service
@Lazy
@ComponentScan
class RedisClient(
    private val redisFaultResolver: AlgorithmDbFaultResolver<AlgorithmResponse>,
    private val cacheRepository: CacheRepository
) {

    fun findAllAlgos(): Iterable<AlgorithmResponse> {
       return cacheRepository.findAll()
    }

    fun findAlgoByName(nameId: String): Result<AlgorithmResponse?, ServiceErrors> = redisFaultResolver {
        cacheRepository.findById(nameId)
    }

    fun putAlgorithm(newAlgorithm: AlgorithmResponse): Result<AlgorithmResponse, ServiceErrors> {
        return try {
            Success(cacheRepository.save(newAlgorithm))
        } catch (ex: Exception) {
            Failure(serviceErrorOf(ServiceError(service = ServiceName.REDIS, errorMessage = ex.localizedMessage, keyInError = "")))
        }
    }

}