package com.algos.mitch.algorithms

fun main(args: Array<String>) {

    val cars1 = mutableListOf(Car(), Car())
    val cars2 : MutableList<Car> = mutableListOf()

    val fords1 = mutableListOf(Ford(), Ford())
    val fords2 : MutableList<Ford> = mutableListOf()

    // copyCars1(fords1, fords2) wont work without Generic - invarient by default. Needs an exact match of type
    // Generics can help us widen or tighten the type parameters
    copyCars2(fords1, fords2) // works because of generics

    copyCars2(fords1, cars1) // doesnt work without covariance




}

//Can you write to the object? Contra-varient (super In) (destination)
//Can you only read from the object? Co-varient ( extends Out) (source)
//read out down the tree (fun return types)
//write in .... up the tree (fun parameter)
//in-varient = exact type only - can go in or OUT

fun copyCars1(source: MutableList<Car>, destination: MutableList<Car>) {
    for (car in source) destination.add(car)
}


fun <T> copyCars2(source: MutableList<out T>, destination: MutableList<T>) {
    for (car in source) destination.add(car)
}



open class Car {

}


class Toyota : Car() {

}


class Ford: Car() {

}





//FLOWER EXAMPLE
open class Flower {

}


class Garden<out T: Flower> {

    val flowers: List<T> = listOf()

    fun pickFlower(i: Int) : T = flowers[i]
//    fun plantFlower(flower: T) wont work with co-varience extends outq

}

class Rose : Flower() {

}


fun waterGarden(garden: Garden<Flower>) {

}

fun tendGarden(garden: Garden<Flower>) {

}