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

        whenever(mockService.processAllAlgorithms()) doReturn listOf(AlgorithmResponse("hello world"))

        val expectedResponse = jacksonObjectMapper().writeValueAsString(AlgorithmResponse("hello world"))
        mockMvc
                .perform(get("/algorithms"))
                .andExpect(status().is2xxSuccessful)
                .andExpect(content().string(expectedResponse))


        verify(mockService, times(1)).processAllAlgorithms()

    }

    @Test
    fun `getAlgorithmByName - should invoke the algo service and pass the providedName`() {

        whenever(mockService.findAlgorithmByName("palindrome")) doReturn AlgorithmResponse("palindrome")

        val expectedResponse = jacksonObjectMapper().writeValueAsString(AlgorithmResponse("palindrome"))

        mockMvc
                .perform(get("/algorithms/palindrome"))
                .andExpect(status().is2xxSuccessful)
                .andExpect(content().string(expectedResponse))

        verify(mockService, times(1)).findAlgorithmByName(any())

    }

    @Test
    fun `getAlgorithmByName - should return 404 NOT_FOUND if algo service returns null`() {

        whenever(mockService.findAlgorithmByName("palindrome")).thenReturn(null)

        val expectedResponse = "Algorithm Not Currently Supported"

        mockMvc
                .perform(get("/algorithms/bacon"))
                .andExpect(status().is4xxClientError)
                .andExpect(content().string(expectedResponse))

        verify(mockService, times(1)).findAlgorithmByName(any())

    }


}