package com.algos.mitch.algorithms

class CodeWarsChallenge3 {

    fun elevator(left: Int, right: Int, floor: Int): String =
            when (checkFloors(left, right, floor)) {
                true -> "left"
                false -> "right"
            }

    private fun checkFloors(left: Int, right: Int, floor: Int): Boolean =
            (Math.abs(left - floor) < Math.abs(right - floor))
}

//fun elevator(left: Int, right: Int, call: Int) = if (Math.abs(left-call) < Math.abs(right-call)) "left" else "right"