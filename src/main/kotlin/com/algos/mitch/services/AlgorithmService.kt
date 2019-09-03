package com.algos.mitch.services

import com.algos.mitch.algorithms.AlgorithmResponse
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ResponseBody


@Service
class AlgorithmService {

    @ResponseBody
    fun processAllAlgorithms(): List<AlgorithmResponse> {
        return algoMap.map { it.value }

    }

    fun findAlgorithmByName(name: String): AlgorithmResponse? {
        return algoMap[name]
    }


    companion object {
        val algoMap = mapOf(
                "hello world" to AlgorithmResponse("hello world", isSolved = false),
                "palindrome" to AlgorithmResponse("palindrome", isSolved = false)
        )
    }
}
