package com.algos.mitch.algorithms

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class AverageArrayTest {

    val subject = AverageArray()


    @Test
    fun `avgArray - given a 2d array, find the sum of each corresponding number and return a single array`() {

        val inputNestedArray = arrayOf(arrayOf(1,2,3,4), arrayOf(5,6,7,8))

        val expectedResult = arrayOf(3,4,5,6)

        val actualResult = subject.avgArray(inputNestedArray)

        assertThat(actualResult).isEqualTo(expectedResult)


    }

    @Test
    fun shouldRepeatString() {
        val expected = "IIIII"

        val actual = subject.repeatStr(5, "I")

        assertThat(actual).isEqualTo("IIIII")

    }


}

