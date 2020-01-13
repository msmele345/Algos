package com.algos.mitch.services

import com.algos.mitch.algo_store.*
import com.algos.mitch.result.*
import com.algos.mitch.test_helpers.UnitTest
import com.nhaarman.mockito_kotlin.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.experimental.categories.Category
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@Category(UnitTest::class)
class AlgorithmServiceTest {

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
            categoryTags = "Tag: Collections, Tag: Data Processing"
        ), AlgorithmSummaryResponse(
            name = "popLast",
            codeSnippet = """
            fun popLast(arr: Array<Int>): Int = arr.dropLast(1)
        """.trimIndent(),
            categoryDescription = "EASY",
            difficultyLevel = 1,
            categoryTags = "Tag: Collections, Tag: Data Processing"
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
            categoryTags = "Tag: Collections, Tag: Data Processing"
        )

        whenever(mockMongoClient.getAlgorithmByName(any())) doReturn Success(mongoResponse)

        whenever(mockResponseTransformer.transform(any())) doReturn expected

        subject.findAlgorithmByName(inputAlgorithmRequest).let { result ->
            assertThat(result.statusCode).isEqualTo(HttpStatus.OK)
            assertThat(result.body).isEqualTo(expected)
        }
    }

    @Test
    fun `addAlgorithm - should invoke the repository create method`() {
        val newAlgorithmDomainModel = AlgorithmDomainModel(
            name = "newStringFilter"
        )

        whenever(mockMongoClient.createAlgorithm(newAlgorithmDomainModel)) doReturn Success(newAlgorithmDomainModel)

        val actual = subject.addAlgorithm(newAlgorithmDomainModel)

        verify(mockMongoClient).createAlgorithm(newAlgorithmDomainModel)
    }

    @Test
    fun `addAlgorithm - should return the object inserted upon success`() {
        val newAlgorithmDomainModel = AlgorithmDomainModel(
            name = "newStringFilter"
        )
        whenever(mockMongoClient.createAlgorithm(newAlgorithmDomainModel)) doReturn Success(newAlgorithmDomainModel)

        val actual = subject.addAlgorithm(newAlgorithmDomainModel)

        assertThat(actual.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(actual.body).isEqualTo(newAlgorithmDomainModel)
    }

    @Test
    fun `addAlgorithm - should return a ServiceError if the mongo operation fails`() {

        val expectedErrors = serviceErrorOf(ServiceError(
            service = ServiceName.MONGO_DB,
            errorType = ErrorType.PERSISTANCE_ERROR
        ))

        val newAlgorithm = AlgorithmDomainModel()
        whenever(mockMongoClient.createAlgorithm(newAlgorithm)) doReturn Failure(expectedErrors)
        whenever(mockErrorMapper.mapErrors(any())) doReturn ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("")

        val actual = subject.addAlgorithm(newAlgorithm)

        assertThat(actual.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }
}