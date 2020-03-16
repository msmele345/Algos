package com.algos.mitch.algorithms

class CountOccurances {


    fun <T> countOccurs(inputList: List<T>): Map<String, String> {
        val map = mutableMapOf<String, String>()
        inputList
            .map { it.toString() }
            .run {
                forEach { element ->
                    if (map.contains(element)) map[element] = (inputList.count { it.toString() == element }).toString()
                    else map[element] = "1"
                }
            }
        return map
    }
}