package com.algos.mitch.algorithms

import com.algos.mitch.test_helpers.UnitTest
import org.assertj.core.api.Assertions.*
import org.junit.Test
import org.junit.experimental.categories.Category

@Category(UnitTest::class)
class EasyStockBrokerTest {

    val subject = EasyStockBroker()

    @Test
    fun `should format the trade confirm into more simple trade report string`() {
        val inputConfirm = "GOOG 100 632.50 B"

        val expectedConfirm = "Buy: 63250 Sell: 0"


         subject.formatTrade(inputConfirm).let { actual ->
             assertThat(actual).isEqualTo(expectedConfirm)
         }
    }


    @Test
    fun `should throw a custom TradeErrorException if the inputTicket is missing values`() {
        val inputConfirm = "GOOG 100 632.50"

        assertThatThrownBy {
            subject.formatTrade(inputConfirm)
        }.satisfies {
            assertThat(it is TradeErrorException)
            assertThat(it.message).isEqualTo("Trade Ticket Error")
        }
    }
}