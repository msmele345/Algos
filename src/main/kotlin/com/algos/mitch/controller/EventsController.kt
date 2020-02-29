package com.algos.mitch.controller

import com.algos.mitch.algo_store.AlgorithmSummaryResponse
import com.algos.mitch.reactive_repo.ReactiveRepository
import org.springframework.http.MediaType
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable
import reactor.core.publisher.Flux


@RestController
class EventsController(
    val eventsRepo: ReactiveRepository
) {
    @CrossOrigin
    @GetMapping("/algorithm/stream",
        produces = [MediaType.TEXT_EVENT_STREAM_VALUE]
    )
    fun feed(model: Model) : Flux<AlgorithmSummaryResponse> {
        return eventsRepo.findAll()
    }
}