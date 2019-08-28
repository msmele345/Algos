package com.algos.mitch.services

import com.algos.mitch.algorithms.AlgorithmResponse
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test
import org.springframework.http.ResponseEntity


class AlgorithmServiceTest {

    val subject = AlgorithmService()

    @Test
    fun `findAllAlgorithms - should return a list of all algorithmResponses` () {
        val expected = ResponseEntity.ok(AlgorithmResponse())

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
    fun `findAlgorithmByName - given an invalid algo name, should throw an illegalArgument exception`() {

        assertThatThrownBy {
            subject.findAlgorithmByName("non-existant algo")
        }.isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("Algorithm not currently supported")


    }
}