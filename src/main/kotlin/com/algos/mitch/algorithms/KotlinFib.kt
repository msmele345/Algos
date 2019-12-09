package com.algos.mitch.algorithms

class KotlinFib {

    fun fibonacci(number: Int): List<Int> {
        if (number <= 1) return listOf(number)
        require(number >= 1)
        return  generateSequence(2 to number) {
            //Generating Sequence starting from 1 {
            it.second to it.first + it.second //Calculating Fn  = Fn-1 + Fn-2 and mapping to return Pair<Int,Int>
        }
            .map { it.second }
            .toList()
    }
}

fun Pair<Int, Int>.mapFibSequence() : Int {
    return 1
}