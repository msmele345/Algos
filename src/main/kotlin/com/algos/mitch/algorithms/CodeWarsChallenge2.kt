package com.algos.mitch.algorithms

class CodeWarsChallenge2 {



    fun solve(inputList: List<Int>): List<Int> = inputList
            .reversed()
            .distinct()
            .reversed()
}
