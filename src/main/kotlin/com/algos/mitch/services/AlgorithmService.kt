package com.algos.mitch.services

import com.algos.mitch.algorithms.AlgorithmResponse
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ResponseBody


@Service
class AlgorithmService {

    @ResponseBody
    fun processAllAlgorithms() : AlgorithmResponse {
        //db call goes here
        return AlgorithmResponse()

    }

    fun findAlgorithmByName(name: String): AlgorithmResponse? {
        return algoMap[name] ?: throw IllegalArgumentException("Algorithm not currently supported")
    }





    companion object {
        val algoMap = mapOf(
                "hello world" to AlgorithmResponse("hello world", isSolved = false),
                "palindrome" to AlgorithmResponse("palindrome", isSolved = false)
        )
    }
}
