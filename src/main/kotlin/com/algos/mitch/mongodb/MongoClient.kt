package com.algos.mitch.mongodb

import com.algos.mitch.algo_store.*
import com.algos.mitch.result.*
import org.springframework.stereotype.Service


@Service
class MongoClient(
    private val mongoRepository: AlgorithmMongoRepository,
    private val mongoErrorHandler: FaultResolver<AlgorithmDomainModel?, ServiceErrors>
) : AlgorithmClient {

    override fun fetchAlgorithms(): Result<Algorithms, ServiceErrors> =
        try {
            val dbResult = mongoRepository.findAll()
            Success(Algorithms(dbResult))
        } catch (e: Exception) {
            Failure(serviceErrorOf(ServiceError(
                service = ServiceName.MONGO_DB,
                errorMessage = e.localizedMessage,
                errorType = ErrorType.UNKNOWN_ERROR
            )))
        }

    override fun getAlgorithmByName(
        keyName: String
    ): Result<AlgorithmDomainModel?, ServiceErrors> = mongoErrorHandler {
        mongoRepository.findById(keyName)
    }

    override fun createAlgorithm(newAlgorithm: AlgorithmDomainModel): Result<AlgorithmDomainModel, ServiceErrors> =
        try {
            Success(mongoRepository.insert(newAlgorithm))
        } catch (ex: Exception) {
            Failure(serviceErrorOf(ServiceError(
                errorType = ErrorType.PERSISTANCE_ERROR,
                errorMessage = ex.localizedMessage,
                service = ServiceName.MONGO_DB
            )))
        }

    fun fetchAllSets(): List<CustomSet> {
        return setGenerator()
    }


    private fun setGenerator(): List<CustomSet> {
        val fakeNames = listOf("Sety", "JavaSet", "NewSet", "KotlinSet", "CoolSet")
        return (0..5).map {
            CustomSet(
                id = createId(),
                name = fakeNames.shuffled().last(),
                codeSnippet = """
                    fun add(element) { subject[size] == element  }
                """.trimIndent()

            )
        }
    }

    private fun createId() = (0..50)
        .toList()
        .shuffled()
        .last()
        .toString()
}