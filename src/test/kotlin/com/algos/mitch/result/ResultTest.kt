package com.algos.mitch.result

import com.algos.mitch.test_helpers.UnitTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.experimental.categories.Category

@Category(UnitTest::class)
class ResultTest {

    @Test
    fun `mapSuccess transforms the success type when the result is a success`() {
        val expected = Success("1")

        val actual = Success(1).mapSuccess { it.toString() }

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `mapSuccess - should transform nothing if result is a Failure`() {
        val expected = Failure(1)
        
        val actual = Failure(1).mapSuccess { toString() }

        assertThat(actual).isEqualTo(expected)
    }
}