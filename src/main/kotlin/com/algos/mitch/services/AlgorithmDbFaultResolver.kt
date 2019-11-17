package com.algos.mitch.services

import com.algos.mitch.algo_store.FaultResolver
import com.algos.mitch.result.*
import org.springframework.stereotype.Service
import java.util.*

@Service
class AlgorithmDbFaultResolver<type> : FaultResolver<type?, ServiceErrors> {
    override fun invoke(call: () -> Any): Result<type?, ServiceErrors> {
        return try {
            (call() as Optional<type?>).let { response ->
                val nullableResponse = if (response.isPresent) response.get() else null
                Success(nullableResponse)
            }
        } catch (e: Exception) {
            Failure(serviceErrorOf(ServiceError(service = ServiceName.MONGO_DB, errorMessage = e.localizedMessage, keyInError = "")))
        }
    }
}
