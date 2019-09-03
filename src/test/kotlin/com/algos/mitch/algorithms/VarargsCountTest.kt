package com.algos.mitch.algorithms

import com.algos.mitch.algorithms.VarargsCount
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class VarargsCountTest {

    val subject = VarargsCount()

    @Test
    fun `argCount - should return the number of passed in params`() {
        val expected = 3

        val actual = subject.argsCount("3", "2", "1")
        assertThat(actual).isEqualTo(3)

    }

}