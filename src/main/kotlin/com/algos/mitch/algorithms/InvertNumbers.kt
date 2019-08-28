package com.algos.mitch.algorithms


class InvertNumbers {


    fun invert(array: IntArray): IntArray = array
            .map { number ->
                if (number <= 0) Math.abs(number) else number * -1
            }.toIntArray()

}