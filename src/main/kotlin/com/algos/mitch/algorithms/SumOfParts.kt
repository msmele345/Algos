package com.algos.mitch.algorithms

class SumOfParts {

    //sum current array and store value
    //remove first item from input array
    //sum new array and store value
    //repeat..

    fun partsSums(arr: IntArray): IntArray {
        val initialSum = arr.sum()
        val finalArray = mutableListOf(initialSum)

        var copyArray = arr.drop(1)

        for (element in copyArray) {
            val sum = copyArray.sum()
            finalArray.add(sum)
            copyArray = copyArray.drop(1)
        }
        finalArray.add(0)

        return finalArray.toIntArray()
    }
}