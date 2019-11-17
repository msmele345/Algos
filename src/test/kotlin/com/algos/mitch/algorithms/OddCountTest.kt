package com.algos.mitch.algorithms

import com.algos.mitch.test_helpers.UnitTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.experimental.categories.Category

@Category(UnitTest::class)
class OddCountTest {

    val subject = OddCount()

    @Test
    fun `oddCount - should return the number of positive odd ints below the provided N`() {

        val inputNumber = 15023

        val actual = subject.oddCounts(inputNumber)

        val expected = 7511

        assertThat(actual).isEqualTo(expected)
    }
}