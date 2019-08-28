package com.algos.mitch.controller

import com.algos.mitch.algorithms.AlgorithmResponse
import com.algos.mitch.services.AlgorithmService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.nhaarman.mockito_kotlin.*
import org.junit.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders


class AlgorithmControllerTest {


    val mockService: AlgorithmService = mock()


    val subject = AlgorithmController(mockService)

    val mockMvc = MockMvcBuilders.standaloneSetup(subject).build()

    @Test
    fun `getAllAlgorithms - should invoke the algo service`() {

        whenever(mockService.processAllAlgorithms()) doReturn AlgorithmResponse("hello world")

        val expectedResponse = jacksonObjectMapper().writeValueAsString(AlgorithmResponse("hello world"))
        mockMvc
                .perform(get("/algorithms"))
                .andExpect(status().is2xxSuccessful)
                .andExpect(content().string(expectedResponse))


        verify(mockService, times(1)).processAllAlgorithms()

    }

}