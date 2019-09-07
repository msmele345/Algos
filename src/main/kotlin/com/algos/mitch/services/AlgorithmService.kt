package com.algos.mitch.services

import com.algos.mitch.algorithms.AlgorithmResponse
import com.algos.mitch.redisClient.RedisClient
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ResponseBody


@Service
class AlgorithmService(
    private val redisClient: RedisClient
) {

    @ResponseBody
    fun processAllAlgorithms(): List<AlgorithmResponse> {
        return algoMap.map { it.value }

    }

    fun findAlgorithmByName(name: String): AlgorithmResponse? {
        return algoMap[name]
    }

    fun findAlgorithmByName2(id: String): AlgorithmResponse? {
        return redisClient.findAlgoByName(id).let { response ->
            when {
                //handle is empty and throw errors if null
                //setup db
                //revisit tymeleaf
                //postgres image?
                response.isPresent -> response.get()
                else -> null
            }
        }
    }


    companion object {
        val algoMap = mapOf(
            "hello world" to AlgorithmResponse("hello world", codeSnippet = """
                    fun helloWorld(): = "Hello World!"
                """.trimIndent(), isSolved = false),
            "palindrome" to AlgorithmResponse("palindrome", isSolved = false)
        )
    }
}
