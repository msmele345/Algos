package com.algos.mitch.algorithms

import org.springframework.data.redis.core.RedisHash
import java.io.Serializable


@RedisHash("AlgorithmResponse")
data class AlgorithmResponse(
        val name: String = "",
        val codeSnippet: String = "",
        val categories: List<String> = emptyList(),
        val isSolved: Boolean = true
) : Serializable


