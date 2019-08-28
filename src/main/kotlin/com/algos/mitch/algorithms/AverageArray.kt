package com.algos.mitch.algorithms

class AverageArray {

    //combine arrays or make pairs
    //find average
    //return single array

    fun avgArray(inputNestedArray: Array<Array<Int>>): Array<Int> {
        val secondArray = inputNestedArray.last()

        val mapOfPairs = inputNestedArray
                .map {
                    it.zip(secondArray)
                }.flatten()

        return mapOfPairs.run {
            map { listOf(it.first, it.second)
                .average().toInt()}
                .take(4)
                .toTypedArray()
        }


    }


    fun repeatStr(r: Int, str: String) : String = StringBuilder().apply {
       (1..r).forEach { _ -> append(str) }
   }.toString()

}