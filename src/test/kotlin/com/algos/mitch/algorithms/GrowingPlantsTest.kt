package com.algos.mitch.algorithms

import com.algos.mitch.test_helpers.UnitTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.experimental.categories.Category

@Category(UnitTest::class)
class GrowingPlantsTest {

    val subject = GrowingPlants()

    @Test
    fun `growingPlants - should return the number of days it takes to reach desiredHeight with inputs`() {
        val expected = 1

        val actual = subject.growingPlant(10, 9, 4)
        assertThat(actual).isEqualTo(expected)
    }
}