package com.algos.mitch.redisClient

import com.algos.mitch.algorithms.AlgorithmResponse
import com.algos.mitch.redis.CacheRepository
import com.algos.mitch.test_helpers.UnitTest
import com.nhaarman.mockito_kotlin.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.experimental.categories.Category
import java.util.*

@Category(UnitTest::class)
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

    @Test
    fun `findAlgoByName - should invoke the repository method findById with the name of the algorithm`() {

        val algoNameId = "hello world"

        subject.findAlgoByName(algoNameId)

        val captor = argumentCaptor<String>()

        verify(mockRepo).findById(captor.capture())

        assertThat(captor.firstValue).isEqualTo(algoNameId)
    }

    @Test
    fun `findAlgoByName - should return the correct algorithm response given a valid name currently in the cache` (){

        val expected = generateAlgorithm("palindrome")

        whenever(mockRepo.findById(any())) doReturn Optional.of(generateAlgorithm("palindrome"))

        val actual = subject.findAlgoByName("palindrome")

        assertThat(actual).isEqualTo(Optional.of(expected))
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