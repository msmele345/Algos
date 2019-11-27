package com.algos.mitch.algorithms

import com.algos.mitch.test_helpers.UnitTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.experimental.categories.Category

@Category(UnitTest::class)
class CamelCaseTest {


    val subject = CamelCase()


    @Test
    fun `toCamelCase - should capitalize every other letter in a string`() {
        val inputString = "camelcase"

        val expected = "cAmElCaSe"

        val actual = subject.toCamelCase(inputString)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `toCamelCase - should capitalize every other letter in a string with 2 chars`() {
        val inputString = "ca"

        val expected = "cA"

        val actual = subject.toCamelCase(inputString)

        assertThat(actual).isEqualTo(expected)
    }


    @Test
    fun `toCamelCase - should return the input string if the length is 1`() {
        val inputString = "c"

        val expected = "c"

        val actual = subject.toCamelCase(inputString)

        assertThat(actual).isEqualTo(expected)
    }

}

