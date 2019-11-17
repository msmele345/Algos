package com.algos.mitch.services

import com.algos.mitch.algo_store.*
import com.algos.mitch.result.Success
import com.algos.mitch.test_helpers.UnitTest
import com.mongodb.client.MongoClient
import com.nhaarman.mockito_kotlin.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.experimental.categories.Category
import org.springframework.http.HttpStatus
import java.util.*

@Category(UnitTest::class)
class AlgorithmServiceTest {

    private val mockRedisClient: com.algos.mitch.redisClient.RedisClient = mock()

    private val mockMongoClient: com.algos.mitch.mongodb.MongoClient = mock()

    private val mockResponseTransformer: AlgorithmDomainToResponseTransformer = mock()

    private val mockBatchResponseTransformer: AlgorithmDomainModelToAlgorithmsTransformer = mock()

    private val mockErrorMapper: AlgorithmErrorMapper = mock()

    val subject = AlgorithmService(
        mockMongoClient,
        mockResponseTransformer,
        mockBatchResponseTransformer,
        mockErrorMapper
    )

    @Test
    fun `processAllAlgorithms - should return a list of all algorithmSummaryResponses`() {
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


        val domainResponses = mutableListOf(AlgorithmDomainModel(
            name = "countDupes",
            codeSnippet = """
            fun countDupes(arr: Array<Int>): Int = arr.size - arr.distinct()
        """.trimIndent(),
            category = com.algos.mitch.algo_store.Category(
                categoryDescription = "EASY",
                difficultyLevel = 2,
                tags = listOf(Tag("Collections"), Tag("Data Processing"))
            )
        ), AlgorithmDomainModel(
            name = "popLast",
            codeSnippet = """
            fun countDupes(arr: Array<Int>): Int = arr.dropLast(1)
        """.trimIndent(),
            category = com.algos.mitch.algo_store.Category(
                categoryDescription = "EASY",
                difficultyLevel = 1,
                tags = listOf(Tag("Collections"), Tag("Data Processing"))
            )
        ))


        whenever(mockMongoClient.fetchAlgorithms()) doReturn Success(Algorithms(algos = domainResponses))

        whenever(mockBatchResponseTransformer.transform(any())) doReturn expectedResponses

        subject.processAllAlgorithms().let { result ->
            assertThat(result.statusCode).isEqualTo(HttpStatus.OK)
            assertThat(result.body).isEqualTo(expectedResponses)
        }

    }

    @Test
    fun `findAlgorithmByName - should return a ResponseEntity of 404_NOT_FOUND when the redisClient returns a success of null`() {
        val inputBadRequest = AlgorithmRequest(nameId = "bacon")

        whenever(mockMongoClient.getAlgorithmByName(any())) doReturn Success(null)

        subject.findAlgorithmByName(inputBadRequest).let { result ->
            assertThat(result.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
        }
    }

    @Test
    fun `findAlgorithmByName - should return a ResponseEntity of 200 Success when the redisClient returns a success `() {

        val inputAlgorithmRequest = AlgorithmRequest(nameId = "popLast")

        val mongoResponse = AlgorithmDomainModel(
            name = "popLast",
            codeSnippet = """
            fun countDupes(arr: Array<Int>): Int = arr.dropLast(1)
        """.trimIndent(),
            category = com.algos.mitch.algo_store.Category(
                categoryDescription = "EASY",
                difficultyLevel = 1,
                tags = listOf(Tag("Collections"), Tag("Data Processing"))
            )
        )

        val expected = AlgorithmSummaryResponse(
            name = "popLast",
            codeSnippet = """
            fun popLast(arr: Array<Int>): Int = arr.dropLast(1)
        """.trimIndent(),
            categoryDescription = "EASY",
            difficultyLevel = 1,
            categoryTags = listOf("Tag: Collections", "Tag: Data Processing")
        )

        whenever(mockMongoClient.getAlgorithmByName(any())) doReturn Success(mongoResponse)

        whenever(mockResponseTransformer.transform(any())) doReturn expected


        subject.findAlgorithmByName(inputAlgorithmRequest).let { result ->
            assertThat(result.statusCode).isEqualTo(HttpStatus.OK)
            assertThat(result.body).isEqualTo(expected)
        }

    }
}