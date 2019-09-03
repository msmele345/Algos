package com.algos.mitch.algorithms

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PrimeFactorTest {


    val subject = PrimeFactor()



    @Test
    fun `should return true if endString ends the main string`() {
        subject.solution("abc", "bc").let {
            assertThat(it).isTrue()
        }
    }



    @Test
    fun `should return the total of the summation`() {
        subject.summation(8).let {
            assertThat(it).isEqualTo(36)
        }
    }

    @Test
    fun `should identify return a list of num if less than or equal to 2`() {

        subject.factorsOf(0).let {
            assertThat(it).isEqualTo(listOf(0))
        }

        subject.factorsOf(1).let {
            assertThat(it).isEqualTo(listOf(1))
        }


    }

    @Test
    fun `should add a collection of numbers that are prime factors to to the result list`() {

        subject.factorsOf(60).let {
            assertThat(it).isEqualTo(listOf(2, 2,3,5 ))
        }

    }

    @Test
    fun `should add prime factors of 18 to a list`() {
        subject.factorsOf(18).let {
            assertThat(it).isEqualTo(listOf(2,3,3))
        }
    }


}