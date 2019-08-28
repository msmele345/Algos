package com.algos.mitch.algorithms

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class MoneyValuesTest {


    @Test
    fun `should return the double value of the money string passed in` () {

        val subject = MoneyValues()

        val actualResult1 = subject.moneyValue("007")
        val actualResult2 = subject.moneyValue("$5.67")
        val actualResult3 = subject.moneyValue("$89")
        val actualResult4 = subject.moneyValue(".11")
        val actualResult5 = subject.moneyValue("-$ 0.1")

        assertThat(actualResult1).isEqualTo(7.0)
        assertThat(actualResult2).isEqualTo(5.67)
        assertThat(actualResult3).isEqualTo(89.0)
        assertThat(actualResult4).isEqualTo(.11)
        assertThat(actualResult5).isEqualTo(-0.10)
    }


}