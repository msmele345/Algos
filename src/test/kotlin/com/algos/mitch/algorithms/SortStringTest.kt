package com.algos.mitch.algorithms

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SortStringTest {


    @Test
    fun `sort-string - give two strings, should return the first string sorted by the second`() {

        val expectedResult = "aaabnn"

        val subject = SortString()

        val actual = subject.sortFirstString("banana", "abn")

        assertThat(actual).isEqualTo(expectedResult)




    }

}