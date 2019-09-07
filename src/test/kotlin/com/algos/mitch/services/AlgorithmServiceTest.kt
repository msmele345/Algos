package com.algos.mitch.services

import com.algos.mitch.algorithms.AlgorithmResponse
import com.algos.mitch.test_helpers.UnitTest
import com.nhaarman.mockito_kotlin.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.experimental.categories.Category
import java.util.*

@Category(UnitTest::class)
class AlgorithmServiceTest {

    val mockRedisClient: com.algos.mitch.redisClient.RedisClient = mock()

    val subject = AlgorithmService(mockRedisClient)

    @Test
    fun `findAllAlgorithms - should return a list of all algorithmResponses`() {
        val expected = listOf(
            AlgorithmResponse("hello world", codeSnippet = """
                    fun helloWorld(): = "Hello World!"
                """.trimIndent()
                , isSolved = false
            ),
            AlgorithmResponse("palindrome", isSolved = false)
        )

        whenever(mockRedisClient.findAllAlgos()) doReturn expected

        subject.processAllAlgorithms().let { result ->
            assertThat(result).isEqualTo(expected)
        }

    }

    @Test
    fun `findAlgorithmByName - given a valid name, should return the corresponding algo`() {
        val expected = AlgorithmResponse(
            name = "palindrome",
            categories = listOf(),
            codeSnippet = """

                """.trimIndent(),
            isSolved = false
        )

        whenever(mockRedisClient.findAlgoByName(any())) doReturn Optional.of(expected)

        subject.findAlgorithmByName("palindrome").let { result ->
            assertThat(result).isEqualTo(expected)
        }

    }

    @Test
    fun `findAlgorithmByName - given an invalid algo name, should return null`() {

        subject.findAlgorithmByName("bacon").let { result ->
            assertThat(result).isNull()
        }

    }
}