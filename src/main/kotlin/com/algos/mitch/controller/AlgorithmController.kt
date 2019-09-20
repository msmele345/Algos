package com.algos.mitch.controller

import com.algos.mitch.result.flatMap
import com.algos.mitch.result.mapFailure
import com.algos.mitch.result.mapSuccess
import com.algos.mitch.services.AlgorithmService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
class AlgorithmController(
        private val algoService: AlgorithmService
) {

    @RequestMapping("/algorithms/all")
    fun getAllAlgorithms(): ResponseEntity<*> {
        return algoService.processAllAlgorithms().let { response ->
            ResponseEntity.ok(response)
        }
    }


    @RequestMapping("/algorithms/{name}")
    fun getAlgorithmByName(
            @PathVariable name: String
    ): ResponseEntity<*> {
            val response = algoService.findAlgorithmByName(name)
            return ResponseEntity.ok(response)

//       return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("")
    }
}

