package com.algos.mitch.algorithms

import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertEquals
import org.junit.Test

class CodeWarsChallengeTest {

    fun testing(actual: String, expected: String) = assertEquals(expected, actual)

    @Test
    fun `should reverse or rotate the string based of the chunk value`() {

        val subject = CodeWarsChallenge()

        var s = "733049910872815764"
        testing(subject.revRot(s, 5), "330479108928157")
        s = "73304991087281576455176044327690580265896"
        testing(subject.revRot(s, 8), "1994033775182780067155464327690480265895")
        s = "73304991087281576455176044327690580265896896028"
        testing(subject.revRot(s, 8), "1994033775182780067155464327690480265895")

    }


    @Test
    fun `should rotate the string by one character` () {
        val subject = CodeWarsChallenge()

        val testString = "73304"


        assertThat(subject.rotateChunk(testString)).isEqualTo("33047")
    }


    @Test
    fun `should join together the result chunks after being rotated`() {
        val subject = CodeWarsChallenge()

        val testList = listOf("682877", "774243", "733517")
        val expected = "330479108928157"

        assertThat(subject.joinChunks(testList)).isEqualTo(expected)

    }

}