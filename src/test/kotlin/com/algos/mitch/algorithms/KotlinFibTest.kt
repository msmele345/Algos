package com.algos.mitch.algorithms

import com.algos.mitch.test_helpers.UnitTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Ignore
import org.junit.Test
import org.junit.experimental.categories.Category

@Category(UnitTest::class)
class KotlinFibTest {

    val subject = KotlinFib()

    @Ignore //TODO re-evaulte the generateSequence function -
    @Test
    fun `fibonacci - should return the fib sequence up to the provided number`() {

        val inputNumber = 12
        val expected = listOf(1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144)

        subject.fibonacci(inputNumber).let { actual ->
            assertThat(actual).isEqualTo(expected)
        }

    }
}