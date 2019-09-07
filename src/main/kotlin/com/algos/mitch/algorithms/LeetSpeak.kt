package com.algos.mitch.algorithms

class LeetSpeak {

    fun encode(source: String?) : String {
       return source?.map { char -> if (mapLeetChar(char) != null) mapLeetChar(char) else char }?.joinToString("") ?: ""
    }

    fun mapLeetChar(letter: Char) : String? {
        val mapOfLeets = mapOf(
            "a" to "4",
            "e" to "3",
            "l" to "1",
            "m" to "/^^\"",
            "o" to "0",
            "u" to "(_)"
        )
        return mapOfLeets[letter.toLowerCase().toString()]
    }
}
