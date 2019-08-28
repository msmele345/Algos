package com.algos.mitch.algorithms

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CountPairsTest {

    val subject = CountPairs()


    @Test
    fun `duplicate - should count the total number of pairs in an array`() {

        val inputArray = intArrayOf(1, 2, 5, 6, 5, 2 )
        val inputArray2 = intArrayOf(1, 2, 2, 20, 6, 20, 2, 6, 2)
        val inputArray3 = intArrayOf(1000, 1000)

//        val actual1 = subject.duplicate(inputArray)
//        assertThat(actual1).isEqualTo(2)

        val actual2 = subject.duplicate(inputArray2)
        assertThat(actual2).isEqualTo(4)

//        val actual3 = subject.duplicate(inputArray3)
//        assertThat(actual3).isEqualTo(1)


    }

    @Test
    fun `duplicate - should count the total number of pairs in an array if all are the same value`() {

        val inputArray = intArrayOf(0, 0, 0, 0, 0, 0)

        val actual = subject.duplicate(inputArray)
        assertThat(actual).isEqualTo(3)

    }
}