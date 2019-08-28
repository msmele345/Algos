package com.algos.mitch.algorithms

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
@EnableAutoConfiguration
class AlgorithmsApplication

fun main(args: Array<String>) {
	runApplication<AlgorithmsApplication>(*args)
}

