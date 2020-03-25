package com.algos.mitch.mongodb

import com.algos.mitch.algo_store.CustomSet
import org.springframework.stereotype.Repository


interface AlgoRepository {
    fun setGenerator(): List<CustomSet>
}

@Repository
class SetRepository : AlgoRepository {


    override fun setGenerator(): List<CustomSet> {
        val fakeNames = listOf("Sety", "JavaSet", "NewSet", "KotlinSet", "CoolSet")
        return (0..5).map {
            CustomSet(
                id = createId(),
                name = fakeNames.shuffled().last(),
                codeSnippet = """
                    fun add(element) { subject[size] == element  }
                """.trimIndent()

            )
        }
    }

    private fun createId() = (0..50)
        .toList()
        .shuffled()
        .last()
        .toString()
}