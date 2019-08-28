package com.algos.mitch.algorithms

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CodeWarsChallenge3Test {

    val subject = CodeWarsChallenge3()


    @Test
    fun `should return the name of the right elevator when that is closest to the floor`() {

        val expected = "right"

        val actual = subject.elevator(0, 1, 1)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should return the name of the left elevator when that is closest to the floor`() {

        val expected = "left"

        val actual = subject.elevator(0, 1, 0)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should return the name of the right elevator again when that is closest to the floor`() {

        val expected = "right"

        val actual = subject.elevator(0, 1, 2)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `should return the name of the right elevator when they are all on the same floor`() {

        val expected = "right"

        val actual = subject.elevator(0, 0, 0)


        assertThat(actual).isEqualTo(expected)
    }


}