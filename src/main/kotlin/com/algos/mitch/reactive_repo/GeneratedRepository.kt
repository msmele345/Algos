package com.algos.mitch.reactive_repo

import com.algos.mitch.algo_store.AlgorithmDomainModel
import com.algos.mitch.algo_store.AlgorithmSummaryResponse
import reactor.core.publisher.Flux

interface GeneratedRepository {

    fun findAll(): Flux<AlgorithmSummaryResponse>
}