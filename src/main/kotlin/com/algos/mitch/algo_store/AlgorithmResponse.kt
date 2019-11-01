package com.algos.mitch.algo_store

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.io.Serializable


@RedisHash("AlgorithmResponse")
data class AlgorithmResponse(
    @Id
    val name: String = "",
    val codeSnippet: String = "",
    val category: Category = Category(),
    val isSolved: Boolean = true
) : Serializable


data class Category(
    val categoryName: String = "",
    val difficultlyLevel: Int = 0,
    val tags: List<Tag> = emptyList()
)

data class Tag(val label: String = "")