package com.algos.mitch.algorithms

class OddCount {
    fun oddCounts(n: Int) : Int = (1.until(n)).count { it % 2 != 0 }
}
