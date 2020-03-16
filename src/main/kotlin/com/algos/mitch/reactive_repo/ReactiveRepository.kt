package com.algos.mitch.reactive_repo

import com.algos.mitch.algo_store.AlgorithmSummaryResponse
import com.algos.mitch.utils.AlgorithmFactory
import com.algos.mitch.utils.AlgorithmFactory.sampleAlgorithm
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import java.time.Duration

@Repository
class ReactiveRepository : GeneratedRepository {

    override fun findAll(): Flux<AlgorithmSummaryResponse> {
        return Flux.interval(Duration.ofSeconds(10))
            .onBackpressureDrop()
            .map(this::generateAlgorithm)
            .flatMapIterable { it }

    }

    private fun generateAlgorithm(interval: Long): List<AlgorithmSummaryResponse> {
        return sampleAlgorithm().shuffled().take(1)
    }
}


