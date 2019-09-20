package com.algos.mitch.algo_store

import com.algos.mitch.result.ErrorType
import com.algos.mitch.result.ServiceErrors
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AlgorithmErrorMapper : ErrorMapper<ResponseEntity<*>>  {

    override fun mapErrors(errors: ServiceErrors): ResponseEntity<*> {
        val serviceError = errors.first()
        return serviceError.let { error ->
            when {
                error.errorType == ErrorType.UNKNOWN_ERROR -> HttpStatus.INTERNAL_SERVER_ERROR to error.generateErrorMessage(error.errorMessage)
                error.errorType == ErrorType.ALGORITHM_NOT_FOUND -> HttpStatus.NOT_FOUND to error.generateErrorMessage(error.errorMessage)
                else -> HttpStatus.BAD_GATEWAY to serviceError.generateErrorMessage("Unknown error has occurred")
            }
        }.let { (response, message) ->
            ResponseEntity.status(response).body(message)
        }
    }
}