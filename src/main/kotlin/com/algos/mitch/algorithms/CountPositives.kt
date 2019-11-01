package com.algos.mitch.algorithms

class CountPositives {
    fun countPositivesSumNegatives(inputArray: Array<Int>): Array<Int> {
        return when {
            inputArray.isEmpty() -> emptyArray()
            else -> {
                val (positives, negatives) = inputArray.partition { it > 0 }
                arrayOf(positives.count(), negatives.sum())
            }
        }
    }
}