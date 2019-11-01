package com.algos.mitch.services

import com.algos.mitch.algo_store.AlgorithmErrorMapper
import com.algos.mitch.algo_store.AlgorithmRequest
import com.algos.mitch.algo_store.AlgorithmResponse
import com.algos.mitch.result.Success
import com.algos.mitch.test_helpers.UnitTest
import com.nhaarman.mockito_kotlin.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.experimental.categories.Category
import org.springframework.http.HttpStatus

@Category(UnitTest::class)
class AlgorithmServiceTest {

    private val mockRedisClient: com.algos.mitch.redisClient.RedisClient = mock()

    private val mockErrorMapper: AlgorithmErrorMapper = mock()

    val subject = AlgorithmService(mockRedisClient, mockErrorMapper)

    @Test
    fun `findAllAlgorithms - should return a list of all algorithmResponses`() {
        val expected = listOf(
            AlgorithmResponse("hello world", codeSnippet = """
                    fun helloWorld(): = "Hello World!"
                """.trimIndent(), isSolved = false
            ),
            AlgorithmResponse("palindrome", isSolved = false)
        )

        whenever(mockRedisClient.findAllAlgos()) doReturn expected

        subject.processAllAlgorithms().let { result ->
            assertThat(result).isEqualTo(expected)
        }

    }
    @Test
    fun `findAlgorithmByName - should return a ResponseEntity of 404_NOT_FOUND when the redisClient returns a success of null`() {
        val inputBadRequest = AlgorithmRequest(nameId = "bacon")

        whenever(mockRedisClient.findAlgoByName(any())) doReturn Success(null)

        subject.findAlgorithmByName(inputBadRequest).let { result ->
            assertThat(result.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
        }
    }

    @Test
    fun `findAlgorithmByName - should return a ResponseEntity of 200 Success when the redisClient returns a success `() {

        val inputAlgorithmRequest = AlgorithmRequest(nameId = "palindrome")

        val expected = AlgorithmResponse(
            name = "palindrome",
            codeSnippet = """

                """.trimIndent(),
            isSolved = false
        )

        whenever(mockRedisClient.findAlgoByName(any())) doReturn Success(expected)


        subject.findAlgorithmByName(inputAlgorithmRequest).let {result ->
            assertThat(result.statusCode).isEqualTo(HttpStatus.OK)
            assertThat(result.body).isEqualTo(expected)
        }

    }
}