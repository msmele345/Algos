package com.algos.mitch.algorithms

import java.io.File

fun main(args: Array<String>) {

    val file = File("testFile.txt")
            .reader()
            .forEachLine { println(it) }
//            .use { it.readText() } //use closes the resource

    val originalString = "BigAssString"
    //evolve the variable
    originalString.let {
        println("This is the original string: $it")
        it.reversed()
    }.let {
        println("This is the string reversed: $it")
        it.length
    }.let {
        println("this is the size of the string: $it")
    }

    //Cant evolve variable with also as it doesnt return a new value
    val originalString2 = "SecondBigString"

    originalString2.also {
        println("This is the orignal also string: $it")
    }.also {
        println("Now we reverse it with also, but it doesnt evolve: ${it.reversed()}")
        it.length
    }.also {
        println("Notice how we have to call it individually each time: ${it.length}")
    }

    //APPLY

    class BadResponse {
        var number: Int? = null
        var prop2: String? = null
        var prop3: String? = null
    }

    /*
        WHEN NOT TO
        Data Object? DON'T USE APPLY
        The object has a constructor? DON'T USE APPLY // copy constructor
        The object has VAL variables:: DON'T USE APPLY
     */

    /*
        WHEN TO USE APPLY
        No constructor
        No val properties
        Not a data object

    */

}





//// Normal approach
//fun makeDir(path: String): File  {
//    val result = File(path)
//    result.mkdirs()
//    return result
//}
//// Improved approach
//fun makeDir(path: String) = path.let{ File(it) }.also{ it.mkdirs() }