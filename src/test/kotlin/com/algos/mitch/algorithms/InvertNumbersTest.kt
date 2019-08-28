package com.algos.mitch.algorithms

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class InvertNumbersTest {

    val subject = InvertNumbers()


    @Test
    fun `invert - should invert each number in the provided list and return the new list`() {

        val expected = intArrayOf(-1, -2, -3)

        val inputIntArray = intArrayOf(1,2,3)

        subject.invert(inputIntArray).let { actual ->
            assertThat(actual).isEqualTo(expected)
        }

    }

    @Test
    fun `invert - given an intArray of negative and positive values, it should invert each correctly`() {
        val expected = intArrayOf(-1, 2, -3, 4, -5)

        val inputIntArray = intArrayOf(1, -2, 3, -4, 5)

        subject.invert(inputIntArray).let { actual ->
            assertThat(actual).isEqualTo(expected)
        }

    }

}