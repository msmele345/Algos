package com.algos.mitch.algorithms

class CamelCase {

    fun toCamelCase(str: String) : String {
        return buildString {
            str.forEachIndexed { index, c ->
                if (index % 2 != 0) append(c.toUpperCase()) else append(c)
            }
        }
    }
}