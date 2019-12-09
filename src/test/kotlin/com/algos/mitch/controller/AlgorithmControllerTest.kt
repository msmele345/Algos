package com.algos.mitch.controller

import com.algos.mitch.algo_store.AlgorithmRequest
import com.algos.mitch.algo_store.AlgorithmResponse
import com.algos.mitch.algo_store.AlgorithmResponses
import com.algos.mitch.algo_store.AlgorithmSummaryResponse
import com.algos.mitch.services.AlgorithmService
import com.algos.mitch.test_helpers.UnitTest
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.nhaarman.mockito_kotlin.*
import org.junit.Test
import org.junit.experimental.categories.Category
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@Category(UnitTest::class)
class AlgorithmControllerTest {

    val mockService: AlgorithmService = mock()

    val subject = AlgorithmController(mockService)

    val mockMvc = MockMvcBuilders
        .standaloneSetup(subject)
        .build()

    @Test
    fun `getAllAlgorithms - should invoke the algo service`() {

        val expectedResponses = AlgorithmResponses(listOf(AlgorithmSummaryResponse(
            name = "countDupes",
            codeSnippet = """
            fun countDupes(arr: Array<Int>): Int = arr.size - arr.distinct(),
        """.trimIndent(),
            categoryDescription = "EASY",
            difficultyLevel = 2,
            categoryTags = listOf("Tag: Collections", "Tag: Data Processing")
        ), AlgorithmSummaryResponse(
            name = "popLast",
            codeSnippet = """
            fun popLast(arr: Array<Int>): Int = arr.dropLast(1)
        """.trimIndent(),
            categoryDescription = "EASY",
            difficultyLevel = 1,
            categoryTags = listOf("Tag: Collections", "Tag: Data Processing")
        )

        ))

        val result = ResponseEntity.status(HttpStatus.OK).body(expectedResponses)
        whenever(mockService.processAllAlgorithms()) doReturn result

        val expectedResult = jacksonObjectMapper().run { writeValueAsString(result.body) }
        mockMvc
            .perform(get("/algorithms/all"))
            .andExpect(status().is2xxSuccessful)
            .andExpect(content().string(expectedResult))


        verify(mockService, times(1)).processAllAlgorithms()
    }

    @Test
    fun `getAlgorithmByName - should invoke the algo service and pass the providedName`() {

        val inputAlgorithmRequest = AlgorithmRequest(nameId = "palindrome")

        val expectedAlgorithmResponse = AlgorithmSummaryResponse("palindrome")

        whenever(mockService.findAlgorithmByName(inputAlgorithmRequest)) doReturn ResponseEntity.ok(expectedAlgorithmResponse)

        val expectedResponse = jacksonObjectMapper().run { writeValueAsString(expectedAlgorithmResponse) }

        mockMvc
            .perform(get("/algorithms/palindrome"))
            .andExpect(status().is2xxSuccessful)
            .andExpect(content().string(expectedResponse))

        verify(mockService, times(1)).findAlgorithmByName(any())
    }

    @Test
    fun `getAlgorithmByName - should return 404 NOT_FOUND if algo service returns null`() {

        val inputBadRequest = AlgorithmRequest(nameId = "bacon")

        whenever(mockService.findAlgorithmByName(inputBadRequest)) doReturn ResponseEntity.status(HttpStatus.NOT_FOUND).body("Algorithm Not Found")

        val expectedResponse = "Algorithm Not Found"

        mockMvc
            .perform(get("/algorithms/bacon"))
            .andExpect(status().is4xxClientError)
            .andExpect(content().string(expectedResponse))

        verify(mockService, times(1)).findAlgorithmByName(any())
    }
}