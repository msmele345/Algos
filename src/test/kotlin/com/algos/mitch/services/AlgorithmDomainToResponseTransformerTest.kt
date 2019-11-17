package com.algos.mitch.services

import com.algos.mitch.algo_store.*
import com.algos.mitch.test_helpers.UnitTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.experimental.categories.Category

@Category(UnitTest::class)
class AlgorithmDomainToResponseTransformerTest {

    val subject = AlgorithmDomainToResponseTransformer()


    @Test
    fun `transform - should map a ADM to an AlgorithmResponse object`() {

        val inputADM = AlgorithmDomainModel(
            name = "countDupes",
            codeSnippet = """
            fun countDupes(arr: Array<Int>): Int = arr.size - arr.distinct()
        """.trimIndent(),
            category = com.algos.mitch.algo_store.Category(
                categoryDescription = "EASY",
                difficultyLevel = 2,
                tags = listOf(Tag("Collections"), Tag("Data Processing"))
            )
        )

        val expectedResponse = AlgorithmSummaryResponse(
            name = "countDupes",
            codeSnippet = """
            fun countDupes(arr: Array<Int>): Int = arr.size - arr.distinct()
        """.trimIndent(),
            categoryDescription = "EASY",
            difficultyLevel = 2,
            categoryTags = listOf("Tag: Collections", "Tag: Data Processing")
        )

        val actual = subject.transform(inputADM)

        assertThat(actual).isEqualToComparingFieldByFieldRecursively(expectedResponse)
    }
}