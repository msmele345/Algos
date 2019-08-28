package com.algos.mitch.algorithms

class SortString {
//    fun sortFirstString(string1: String, string2: String): String {
//        return string1.toList().joinToString().run {
//            toList()
//        }.let {
//            it.sorted()
//        }.takeLast(string1.length).joinToString("")
//    }


    fun sortFirstString(string1: String, string2: String): String {
        val a = string1.toCharArray()
        val b  = string2.toCharArray()

        val map = a.zip(b).toMap()

        val sorts = a.sortedBy { map.keys.size }

        val sortedValues = string1.toList().zip(string2.toList())
      return ""
    }



}