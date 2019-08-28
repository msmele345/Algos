package com.algos.mitch.algorithms


class CountPairs {
    fun duplicate(array: IntArray): Int = when {
        array.all { it == array.last() } -> array.size / 2
        array.isEmpty() ||  array.size == 1 -> 0
        else ->  {
            countDupes(array)
        }
    }
}

private fun countDupes(array: IntArray) : Int {

    var multipleDupesCount = 0

    val allDupes = array.toList().groupingBy { it }.eachCount().filterValues { it > 1 }
    val singleDupes = array.toList().groupingBy { it }.eachCount().filterValues { it == 2 }

    allDupes.values.forEach {
        if (it > 2) multipleDupesCount += it / 2
    }

    return singleDupes.size + multipleDupesCount
}


//codeWars answers
fun duplicates(array: IntArray) = array.groupBy { it }.map { it.value.size / 2 }.sum()

fun duplicate(array: IntArray) = array.groupBy { it }.map { it.value.size / 2 }.sum()

//simple count  = listOf("apple", "orange", "apple").count { it == "apple" }


//classic show duplicates
private fun dupesArray(array: IntArray): List<Int> {
    val newList = mutableListOf<Int>()

    array.forEach {
        if (!newList.contains(it))
            newList.add(it)
    }

    return newList
}
