package com.algos.mitch.services

import com.algos.mitch.algorithms.AlgorithmResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class AlgorithmServiceTest {


    val subject = AlgorithmService()

    @Test
    fun `findAllAlgorithms - should return a list of all algorithmResponses`() {
        val expected = listOf(
                AlgorithmResponse("hello world", isSolved = false),
                AlgorithmResponse("palindrome", isSolved = false)
        )

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