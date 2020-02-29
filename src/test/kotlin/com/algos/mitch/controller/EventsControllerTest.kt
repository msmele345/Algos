package com.algos.mitch.controller

import com.algos.mitch.algo_store.AlgorithmSummaryResponse
import com.algos.mitch.reactive_repo.ReactiveRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import reactor.core.publisher.Flux

class EventsControllerTest {

    private val mockEventsRepo: ReactiveRepository = mock()

    val subject = EventsController(mockEventsRepo)

    val mockMvc = MockMvcBuilders
        .standaloneSetup(subject)
        .build()


    @Test
    fun `feed - success - should call the reactive repo for AlgoirthmEvents`() {

        val fluxResponse = AlgorithmSummaryResponse(name = "testAlgo")
        whenever(mockEventsRepo.findAll()) doAnswer {
           Flux.create {
               it.next(fluxResponse)
               it.complete()
           }
        }

        mockMvc
            .perform {
                MockMvcRequestBuilders.get("/algorithm/stream")
                    .accept(MediaType.TEXT_EVENT_STREAM_VALUE)
                    .buildRequest(it)
            }
            .andExpect {
                status().is2xxSuccessful
                content().string(jacksonObjectMapper().writeValueAsString(fluxResponse))
            }

        verify(mockEventsRepo).findAll()
    }
}