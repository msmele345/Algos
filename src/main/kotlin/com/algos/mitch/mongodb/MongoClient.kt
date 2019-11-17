package com.algos.mitch.mongodb

import com.algos.mitch.algo_store.AlgorithmClient
import com.algos.mitch.algo_store.AlgorithmDomainModel
import com.algos.mitch.algo_store.Algorithms
import com.algos.mitch.algo_store.FaultResolver
import com.algos.mitch.result.*
import org.springframework.stereotype.Service


@Service
class MongoClient(
    private val mongoRepository: AlgorithmMongoRepository,
    private val mongoErrorHandler: FaultResolver<AlgorithmDomainModel?, ServiceErrors>
) : AlgorithmClient {

    override fun fetchAlgorithms(): Result<Algorithms, ServiceErrors> {
        return try {
            val dbResult = mongoRepository.findAll()
            Success(Algorithms(dbResult))
        } catch (e: Exception) {
            Failure(serviceErrorOf(ServiceError(
                service = ServiceName.MONGO_DB,
                errorMessage = e.localizedMessage,
                errorType = ErrorType.UNKNOWN_ERROR
            )))
        }
    }

    override fun getAlgorithmByName(keyName: String): Result<AlgorithmDomainModel?, ServiceErrors> = mongoErrorHandler {
        mongoRepository.findById(keyName)
    }
}