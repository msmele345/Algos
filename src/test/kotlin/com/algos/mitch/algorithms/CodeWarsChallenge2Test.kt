package com.algos.mitch.algorithms

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CodeWarsChallenge2Test {

    @Test
    fun `should remove all the left most duplicates from the array` () {
        val subject = CodeWarsChallenge2()

        val inputList = listOf(3,4,4,3,6,3)

        subject.solve(inputList).let {
            assertThat(it).isNotNull
            assertThat(it).isEqualTo(listOf(4,6,3))
        }


    }

}