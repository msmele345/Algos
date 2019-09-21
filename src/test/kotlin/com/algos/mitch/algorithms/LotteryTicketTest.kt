package com.algos.mitch.algorithms

import com.algos.mitch.test_helpers.UnitTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.experimental.categories.Category

@Category(UnitTest::class)
class LotteryTicketTest {

    val subject = LotteryTicket()


    @Test
    fun `mapCodes - should map every letter in the alphabet to its corresponding ACSII code `() {

        val expected = 65

        val actual = subject.mapCodes()

        assertThat(actual.values.first()).isEqualTo(expected)

        val values = actual.values.toList()

        val keys = actual.keys.toList()
        assertThat(keys[3]).isEqualTo("D")
        assertThat(values[3]).isEqualTo(68)
    }


    @Test
    fun `countWins - should split the characters in the Pair and check and compare to the provided number`() {

        val inputPair = Pair("ABC", 65)

        val letterA = subject.countWins(inputPair)
        val letterC = subject.countWins(inputPair)
        val letterB = subject.countWins(inputPair)

        assertThat(letterA).isTrue()
        assertThat(letterC).isTrue()
        assertThat(letterB).isTrue()

    }


    @Test
    fun `bingo - should return a WINNER string if the amount of wins from each ticket is greater than or equal to the provided win count `() {
        val inputTickets = arrayOf(
            Pair("ABC", 65),
            Pair("HGR", 74),
            Pair("BYHT", 74)
        )
        val actual = subject.bingo(inputTickets, 1)

        assertThat(actual).isEqualTo("Winner!")
    }


    @Test
    fun `bingo - should return a LOSER string if the amount of wins from each ticket is less than the provided win count `() {
        val inputTickets = arrayOf(
            Pair("ABC", 65),
            Pair("HGR", 74),
            Pair("BYHT", 74)
        )

        val inputTickets2 = arrayOf(
            "HGTYRE" to 74,
            "BE" to 66,
            "JKTY" to 74
        )

        val actual = subject.bingo(inputTickets, 2)
        val actual2 = subject.bingo(inputTickets2, 3)

        assertThat(actual).isEqualTo("Loser!")
        assertThat(actual2).isEqualTo("Loser!")
    }

    @Test
    fun `superIdiomKotlinAlternative - does all of the things in one fancy kotlin function` () {
        val inputTickets = arrayOf(
            Pair("ABC", 65),
            Pair("HGR", 74),
            Pair("BYHT", 74)
        )

        val actual = subject.superIdiomKotlinAlternative(inputTickets, 2)

        val newArray = Array(25) {
            65.rangeTo(90)
        }

        println(newArray.clone())

        assertThat(actual).isEqualTo("Looser!")


    }
}