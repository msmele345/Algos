package com.algos.mitch.redisClient

import com.algos.mitch.algorithms.AlgorithmResponse
import com.algos.mitch.redis.CacheRepository
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class RedisClientTest {

    val mockRepo: CacheRepository = mock()

    val subject = RedisClient(mockRepo)


    @Test
    fun `findAllAlgos - should call the repository findAll`() {

        subject.findAllAlgos()

        verify(mockRepo, times(1)).findAll()

    }


    @Test
    fun `findAllAlgos - should return all algos in the cache`() {

        val mockCache: CacheRepository = mock {
            on { findAll() } doReturn listOf(
                    generateAlgorithm(),
                    generateAlgorithm("palindrome")
            )
        }

        val expected = listOf(
                generateAlgorithm(),
                generateAlgorithm("palindrome")
        )

        val subject = RedisClient(mockCache)

        val actual = subject.findAllAlgos()


        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected)

    }


    private fun generateAlgorithm(
            name: String = "hello world",
            categories: List<String> = listOf("easy"),
            codeSnippet: String = "",
            isSolved: Boolean = false

    ): AlgorithmResponse {
        return AlgorithmResponse(name, codeSnippet, categories, isSolved)
    }


}