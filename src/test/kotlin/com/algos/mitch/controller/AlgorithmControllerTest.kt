package com.algos.mitch.controller

import com.algos.mitch.algo_store.*
import com.algos.mitch.services.AlgorithmService
import com.algos.mitch.test_helpers.UnitTest
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.nhaarman.mockito_kotlin.*
import org.junit.Test
import org.junit.experimental.categories.Category
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.lang.RuntimeException

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
            categoryTags = "Tag: Collections, Tag: Data Processing",
            isSolved = false
        ), AlgorithmSummaryResponse(
            name = "popLast",
            codeSnippet = """
            fun popLast(arr: Array<Int>): Int = arr.dropLast(1)
        """.trimIndent(),
            categoryDescription = "EASY",
            difficultyLevel = 1,
            categoryTags = "Tag: Collections, Tag: Data Processing",
            isSolved = false
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

        val expectedAlgorithmResponse = AlgorithmSummaryResponse(name = "palindrome", isSolved = false)

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


    @Test
    fun `updateAlgorithms - should invoke the algo service for updates`() {
        val expected = AlgorithmSummaryResponse(isSolved = false)

        whenever(mockService.addAlgorithm(any())) doReturn ResponseEntity.ok(expected)

        val inputAlgo = AlgorithmDomainModel().copy(
            name = "filterList"
        ).run {
            jacksonObjectMapper().writeValueAsString(this)
        }

        mockMvc
            .perform(
                post("/addAlgorithm")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(inputAlgo)
            )
            .andExpect(status().is2xxSuccessful)

        verify(mockService).addAlgorithm(any())
    }

    @Test
    fun getSets_success_shouldReturn200_with_ListOfSets() {


        val expected = ResponseEntity.ok(listOf(
            CustomSet(name = "CoolSet"),
            CustomSet(name = "JavaSet")
        ))
        whenever(mockService.processAllSets()) doReturn expected

        mockMvc
            .perform {
                MockMvcRequestBuilders.get("/sets/all")
                    .accept(MediaType.APPLICATION_JSON)
                    .buildRequest(it)
            }
            .andExpect {
                status().is2xxSuccessful
                content().string(
                    jacksonObjectMapper()
                        .run {
                            writeValueAsString(listOf(
                                CustomSet(name = "CoolSet"),
                                CustomSet(name = "JavaSet")
                            ))
                        })
            }
        verify(mockService).processAllSets()
    }


    @Test
    fun `getSets - failure - shouldReturn500IfServiceThrowsException`() {

        whenever(mockService.processAllSets()) doReturn ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("some error occured")

        mockMvc
            .perform {
                MockMvcRequestBuilders.get("/sets/all")
                    .buildRequest(it)
            }
            .andExpect {
                status().is5xxServerError
                content().string("some error occured")
            }
    }

    @Test
    fun `getSetbyId - should return a 200 with a set that matches the provided Id`() {

        val expected = CustomSet(id = "4", name = "CoolSet")

        whenever(mockService.findSetById(any())) doReturn ResponseEntity
            .status(HttpStatus.OK)
            .body(expected)

        mockMvc
            .perform(get("/sets/4"))
            .andExpect(status().is2xxSuccessful)
            .andExpect(content().string(jacksonObjectMapper().writeValueAsString(expected)))
    }
}

//NEED NEGATIVE TESTS NEXT