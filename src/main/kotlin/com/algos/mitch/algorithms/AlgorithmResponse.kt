package com.algos.mitch.algorithms



data class AlgorithmResponse(
        val name: String = "",
        val codeSnippet: String = "",
        val categories: List<String> = emptyList(),
        val isSolved: Boolean = true
)