package com.algos.mitch.algorithms

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class LeetSpeakTest {

    val subject = LeetSpeak()

    @Test
    fun `encode - should convert letters in string that conform with leetspeak versions`() {

        val inputString = "abcdef"

        val expectedStringResult = "4bcd3f"

        subject.encode(inputString).let { result ->
            assertThat(result).isEqualTo(expectedStringResult)
        }

    }

    @Test
    fun `mapLeetChar - should map a given valid string to its corresponding leet char, case insensitive`() {
        val input1: Char = 'a'
        val input2: Char = 'E'
        val input3: Char = 'm'


        val result1 = subject.mapLeetChar(input1)
        assertThat(result1).isEqualTo("4")

        val result2 = subject.mapLeetChar(input2)
        assertThat(result2).isEqualTo("3")

        val result3 = subject.mapLeetChar(input3)
        assertThat(result3).isEqualTo("/^^\"")
    }
}