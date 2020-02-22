package com.algos.mitch.algorithms

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SumOfPartsTest {

    val subject = SumOfParts()

    @Test
    fun `partsSums - success - should sum the value of each sub array created when removing value at first index`() {
        val inputIntArray = intArrayOf(0,1,3,6,10)

        val input2 = intArrayOf(1, 2, 3, 4, 5, 6)

        val expected = intArrayOf(20,20,19,16,10,0)

        val expected2= intArrayOf(21, 20, 18, 15, 11, 6, 0)

        val actual = subject.partsSums(inputIntArray)

        val actual2 = subject.partsSums(input2)

        assertThat(actual).isEqualTo(expected)
        assertThat(actual2).isEqualTo(expected2)
    }
}