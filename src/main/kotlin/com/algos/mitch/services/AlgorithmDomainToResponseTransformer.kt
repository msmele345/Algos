package com.algos.mitch.services

import com.algos.mitch.algo_store.*
import org.springframework.stereotype.Service


@Service
class AlgorithmDomainToResponseTransformer : Transformer<AlgorithmDomainModel, AlgorithmSummaryResponse> {
    override fun transform(from: AlgorithmDomainModel): AlgorithmSummaryResponse {
        return AlgorithmSummaryResponse(
            name = from.name,
            codeSnippet = from.codeSnippet,
            categoryDescription = from.category.categoryDescription,
            difficultyLevel = from.category.difficultyLevel,
            categoryTags = from.displayTags(),
            isSolved = from.isSolved
        )
    }
}

fun AlgorithmDomainModel.displayTags(): String {
    return this.category.tags.joinToString { tag -> "Tag: ${tag.label}" }
}