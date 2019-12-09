package com.algos.mitch.controller

import com.algos.mitch.algo_store.AlgorithmRequest
import com.algos.mitch.services.AlgorithmService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
class AlgorithmController(
        private val algoService: AlgorithmService
) {

    @RequestMapping("/algorithms/all")
    fun getAllAlgorithms(): ResponseEntity<*> {
        return algoService.processAllAlgorithms().let { response ->
            ResponseEntity.ok(response.body ?: "")
        }
    }


    @RequestMapping("/algorithms/{name}")
    fun getAlgorithmByName(
            @PathVariable name: String
    ): ResponseEntity<*> {
        val request = AlgorithmRequest(nameId = name)
        return algoService.findAlgorithmByName(request)
    }
}

