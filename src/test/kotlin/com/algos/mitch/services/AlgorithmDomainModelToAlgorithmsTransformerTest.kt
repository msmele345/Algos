package com.algos.mitch.services

import com.algos.mitch.algo_store.*
import com.algos.mitch.test_helpers.UnitTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.experimental.categories.Category
import java.util.*

@Category(UnitTest::class)
class AlgorithmDomainModelToAlgorithmsTransformerTest {


    val subject = AlgorithmDomainModelToAlgorithmsTransformer()

    val uuid1 = UUID.randomUUID()
    val uuid2 = UUID.randomUUID()

    val domainResponses = Algorithms(listOf(AlgorithmDomainModel(
        id = uuid1,
        name = "countDupes",
        codeSnippet = """
            fun countDupes(arr: Array<Int>): Int = arr.size - arr.distinct()
        """.trimIndent(),
        category = com.algos.mitch.algo_store.Category(
            categoryDescription = "EASY",
            difficultyLevel = 2,
            tags = listOf(Tag("Collections"), Tag("Data Processing"))
        ),
        isSolved = false
    ), AlgorithmDomainModel(
        id = uuid2,
        name = "popLast",
        codeSnippet = """
            fun popLast(arr: Array<Int>): Int = arr.dropLast(1)
        """.trimIndent(),
        category = com.algos.mitch.algo_store.Category(
            categoryDescription = "EASY",
            difficultyLevel = 1,
            tags = listOf(Tag("Collections"), Tag("Data Processing"))
        ),
        isSolved = false
    )))


    val expectedResponses = AlgorithmResponses(
        listOf(AlgorithmSummaryResponse(
            id = uuid1,
            name = "countDupes",
            codeSnippet = """
            fun countDupes(arr: Array<Int>): Int = arr.size - arr.distinct()
        """.trimIndent(),
            categoryDescription = "EASY",
            difficultyLevel = 2,
            categoryTags = "Tag: Collections, Tag: Data Processing",
            isSolved = false
        ), AlgorithmSummaryResponse(
            id = uuid2,
            name = "popLast",
            codeSnippet = """
            fun popLast(arr: Array<Int>): Int = arr.dropLast(1)
        """.trimIndent(),
            categoryDescription = "EASY",
            difficultyLevel = 1,
            categoryTags = "Tag: Collections, Tag: Data Processing",
            isSolved = false
        )))


    @Test
    fun `transform - should map a list of algorithms to a list of AlgorithmSummaryResponses`() {

        val actual = subject.transform(domainResponses)
        assertThat(actual).isEqualTo(expectedResponses)
    }

    @Test
    fun `transform - respones should now contain isSolved boolean`() {
        val actual = subject.transform(domainResponses)

        actual.run {
            forEach {
                assertThat(it.isSolved).isEqualTo(false)
            }
        }
    }
}