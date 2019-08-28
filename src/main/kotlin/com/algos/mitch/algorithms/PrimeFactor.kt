package com.algos.mitch.algorithms

class PrimeFactor {

    fun factorsOf(tempNum: Int): List<Int> {
        var remainder = tempNum
        var divisor = 2
        if (remainder < 2) return listOf(remainder)
        val resultList = mutableListOf<Int>()


        while (remainder > 1) {
            while (remainder % divisor == 0) {
                resultList.add(divisor)
                remainder /= divisor

            }
            divisor++
        }
        return resultList
    }


    fun summation(number: Int) : Int = (0..number).map { it }.sum()
//    fun summation(n: Int) = (1..n).sum()
//    return 1.rangeTo(n).sum();


    fun solution(fullString: String, endString: String) : Boolean
            = fullString.takeLast(endString.length) == endString



}


