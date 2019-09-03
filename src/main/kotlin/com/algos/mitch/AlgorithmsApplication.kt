package com.algos.mitch.algorithms

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.algos.*")
class AlgorithmsApplication

fun main(args: Array<String>) {
	runApplication<AlgorithmsApplication>(*args)
}