package com.algos.mitch.services

import com.algos.mitch.algo_store.*
import org.springframework.stereotype.Service


@Service
class AlgorithmDomainModelToAlgorithmsTransformer : Transformer<Algorithms, AlgorithmResponses> {
    override fun transform(from: Algorithms): AlgorithmResponses {
        return AlgorithmResponses(from.algos.map { domainAlgorithm ->
            AlgorithmSummaryResponse(
                name = domainAlgorithm.name,
                codeSnippet = domainAlgorithm.codeSnippet,
                categoryDescription = domainAlgorithm.category.categoryDescription,
                difficultyLevel = domainAlgorithm.category.difficultyLevel,
                categoryTags = domainAlgorithm.displayTags()
            )
        })
    }
}
