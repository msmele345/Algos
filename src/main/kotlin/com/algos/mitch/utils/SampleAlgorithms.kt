package com.algos.mitch.utils

import com.algos.mitch.algo_store.AlgorithmSummaryResponse

fun sampleAlgorithm(): List<AlgorithmSummaryResponse> {
    return listOf(
        AlgorithmSummaryResponse(
            name = "getLastElement",
            codeSnippet = """
            fun parseString(arr: List<String>): String = arr.last()
        """.trimIndent(),
            categoryDescription = "EASY",
            difficultyLevel = 1,
            categoryTags = "Tag: Arrays, Tag: Data Processing"
        ),
        AlgorithmSummaryResponse(
            name = "getSize",
            codeSnippet = """
            fun getSize(arr: Array<Int>): Int = arr.size
        """.trimIndent(),
            categoryDescription = "HARD",
            difficultyLevel = 4,
            categoryTags = "Tag: Collections, Tag: Data Processing"
        ),
        AlgorithmSummaryResponse(
            name = "findFirst",
            codeSnippet = """
            fun countDupes(arr: Array<Int>): Int = arr.first()
        """.trimIndent(),
            categoryDescription = "MEDIUM",
            difficultyLevel = 3,
            categoryTags = "Tag: Processing, Tag: Parse Collections"
        ),
        AlgorithmSummaryResponse(
            name = "updateString",
            codeSnippet = """
            fun countDupes(str: String, newChar: String): String = str.replace(str, newChar)
        """.trimIndent(),
            categoryDescription = "EXTREME PROGRAMMING",
            difficultyLevel = 5,
            categoryTags = "Tag: Strings, Tag: Data Processing"
        ),
        AlgorithmSummaryResponse(
            name = "countDupes",
            codeSnippet = """
            fun countDupes(arr: Array<Int>): Int = arr.size - arr.distinct()
        """.trimIndent(),
            categoryDescription = "EASY",
            difficultyLevel = 2,
            categoryTags = "Tag: Collections, Tag: Collection Analytics"
        )
    )
}


fun generateAlgorithm(interval: Long): List<AlgorithmSummaryResponse> {
    return sampleAlgorithm().shuffled().take(1)
}

