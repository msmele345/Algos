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

interface Transformer<in IN_OBJECT, out OUT_OBJECT> {
    fun transform(from: IN_OBJECT): OUT_OBJECT
}


interface DataBag

interface NullDataBag: DataBag

interface AlgorithmClient {
    fun fetchAlgorithms(): Result<Algorithms, ServiceErrors>
    fun getAlgorithmByName(keyName: String): Result<AlgorithmDomainModel?, ServiceErrors>
    fun createAlgorithm(newAlgorithm: AlgorithmDomainModel): Result<AlgorithmDomainModel, ServiceErrors>
}