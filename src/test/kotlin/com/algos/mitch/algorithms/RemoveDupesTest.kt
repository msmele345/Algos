package com.algos.mitch.algorithms

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class RemoveDupesTest {

    val subject = RemoveDupes()


    @Test
    fun `removeDuplicateStrings - should remove all duplicate words`() {

        val expected = "alpha beta gamma delta"

        val inputString = "alpha beta beta gamma gamma gamma delta alpha beta beta gamma gamma gamma"


        val actual = subject.removeDuplicates(inputString)

        assertThat(actual).isEqualTo(expected)



    }


}