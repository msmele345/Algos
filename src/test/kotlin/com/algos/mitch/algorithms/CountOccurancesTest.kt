package com.algos.mitch.algorithms

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CountOccurancesTest {


    val subject = CountOccurances()

    @Test
    fun `count - should count the number of times each element appears in the list`() {
        val inputList = listOf(2, 2, 2, 2, 78, 9912, 44, 65, 65)

        val expected = mapOf(
            "2" to "4",
            "78" to "1",
            "9912" to "1",
            "44" to "1",
            "65" to "2"
        )

        val actual = subject.countOccurs(inputList)

        assertThat(actual).isEqualTo(expected)
    }
}