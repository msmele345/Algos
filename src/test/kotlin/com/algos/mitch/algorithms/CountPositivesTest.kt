package com.algos.mitch.algorithms

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CountPositivesTest {

    //count all positive numbers in array, sum the negative values

    val subject = CountPositives()

    @Test
    fun `countPositivesSumNegatives - should return an array with the count and sum`() {
        val inputArray = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -11, -12, -13, -14, -15)

        val expected = arrayOf(10, -65)

        val actual = subject.countPositivesSumNegatives(inputArray)

        assertThat(actual).isEqualTo(expected)
    }
}