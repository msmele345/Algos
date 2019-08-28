package com.algos.mitch.algorithms

class RemoveDupes {

    fun removeDuplicates(inputString: String): String {

        val dupesArray = mutableListOf<String>()

        inputString.split(" ").run {
            forEach { word ->
                if (!dupesArray.contains(word)) dupesArray.add(word)
            }
        }

        return dupesArray.joinAndFilterCommas()
    }

    private fun<T> MutableList<T>.joinAndFilterCommas(): String {
        return this.joinToString().filter { it != ',' }
    }
}