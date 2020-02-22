package com.algos.mitch.algo_store

import org.springframework.data.annotation.Id
import java.io.Serializable

//@RedisHash("AlgorithmResponse")
data class AlgorithmResponse(
    @Id
    val name: String = "",
    val codeSnippet: String = "",
    val category: Category = Category(),
    val isSolved: Boolean = true
) : Serializable


data class AlgorithmSummaryResponse(
    val name: String = "",
    val codeSnippet: String = "",
    val categoryDescription: String = "",
    val difficultyLevel: Int? = null,
    val categoryTags: String = "",
    val isSolved: Boolean = false
)