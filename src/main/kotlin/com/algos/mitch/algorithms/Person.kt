package com.algos.mitch.algorithms

data class Person(val firstName: String = "",
                  val lastName: String = "",
                  val age: Int = 0
) {

    fun mergeLists(list1: List<Int>, list2: List<Int>): List<Int> =
            list1.filter { it in list2 }


}


class BadResponse {
    var number: Int? = null
    var prop2: String? = null
    var prop3: String? = null
}

class Box<out T> {

}

open class Paper {


}

class Regular : Paper() {

}

class Premium : Paper() {

}