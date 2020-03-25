package com.algos.mitch.controller

import com.algos.mitch.algo_store.AlgorithmDomainModel
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

    @RequestMapping("/addAlgorithm", method = [RequestMethod.POST])
    @ResponseBody
    fun insertAlgorithm( //or @RequestBody
        @ModelAttribute algorithm: AlgorithmDomainModel
    ): ResponseEntity<*> {
        return algoService.addAlgorithm(algorithm)
    }

    @RequestMapping("/sets/all", method = [RequestMethod.GET])
    fun getAllSets(): ResponseEntity<*> {
        return algoService.processAllSets()
    }

    @RequestMapping("/sets/{id}", method = [RequestMethod.GET])
    fun getSetById(@PathVariable id: String): ResponseEntity<*> {
        return algoService.findSetById(id)
    }
}
