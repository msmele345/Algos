package com.algos.mitch.algo_store

import com.algos.mitch.result.Result
import com.algos.mitch.result.ServiceErrors
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable

@Document(collection = "algorithmDomainModels")
data class AlgorithmDomainModel(
    @Id
    val name: String = "",
    val codeSnippet: String = "",
    val category: Category = Category(categoryDescription = ""),
    val isSolved: Boolean = true
) : Serializable

data class Category(
    val categoryDescription: String = "",
    val difficultyLevel: Int = 1,
    val tags: List<Tag> = emptyList()
)

data class Tag(val label: String = "")

enum class CategoryDescription(val value: String) {
    HARD("HARD"),
    EASY("EASY"),
    MEDIUM("MEDIUM"),
    EXTREME_PROGRAMMING("EXTREME PROGRAMMING")
}


data class Algorithms(
    val algos: List<AlgorithmDomainModel>
) : List<AlgorithmDomainModel> by algos

data class AlgorithmResponses(
    val listOfAlgos: List<AlgorithmSummaryResponse>
) : List<AlgorithmSummaryResponse> by listOfAlgos