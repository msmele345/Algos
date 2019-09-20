package com.algos.mitch.algo_store

import com.algos.mitch.result.Result
import com.algos.mitch.result.ServiceErrors


interface ErrorHandler<SUCCESS, out FAILURE>

interface FaultResolver<SUCCESS, out FAILURE> : ErrorHandler<SUCCESS, FAILURE> {
    operator fun invoke(call: () -> Any): Result<SUCCESS, FAILURE>
}

interface ErrorMapper<out RESPONSE> {
    fun mapErrors(errors: ServiceErrors): RESPONSE
}