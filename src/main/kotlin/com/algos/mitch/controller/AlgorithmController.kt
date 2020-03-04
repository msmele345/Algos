package com.algos.mitch.controller

import com.algos.mitch.algo_store.AlgorithmDomainModel
import com.algos.mitch.algo_store.AlgorithmRequest
import com.algos.mitch.algo_store.AlgorithmSummaryResponse
import com.algos.mitch.mongodb.AlgorithmMongoRepository
import com.algos.mitch.reactive_repo.GeneratedRepository
import com.algos.mitch.reactive_repo.ReactiveRepository
import com.algos.mitch.services.AlgorithmService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable
import reactor.core.publisher.Flux


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

}
