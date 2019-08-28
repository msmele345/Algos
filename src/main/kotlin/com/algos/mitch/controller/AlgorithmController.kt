package com.algos.mitch.controller

import com.algos.mitch.services.AlgorithmService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping
class AlgorithmController(
        private val algoService: AlgorithmService
) {

    @GetMapping("/algorithms")
    fun getAllAlgorithms(): ResponseEntity<*> {
        return algoService.processAllAlgorithms().let { response ->
            ResponseEntity.ok(response)
        }
    }


}