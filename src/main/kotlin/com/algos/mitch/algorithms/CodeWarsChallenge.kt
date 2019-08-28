package com.algos.mitch.algorithms

class CodeWarsChallenge {

    fun revRot(stringNumber: String, chunkSize: Int): String {

        val chunkResult = (0 until (stringNumber.length / chunkSize)).map {
           stringNumber.substring(it * chunkSize, (it + 1) * chunkSize)
        }
        return joinChunks(chunkResult)
    }

    fun rotateChunk(stringNum: String) : String = stringNum.drop(1) + stringNum.first()

    fun joinChunks(listOfChunksCubes: List<String>): String = listOfChunksCubes.map {
        if (sumCubes(it) % 2 == 0) it.reversed() else rotateChunk(it)
    }.joinToString("")


    private fun cubeNumber(num: Int) : Double = Math.pow(num.toDouble(), 3.0)

    private fun sumCubes(numString: String) : Int {
        var sum : Double = 0.0
        numString.forEach {
            sum += cubeNumber(it.toString().toInt())
        }
        return sum.toInt()
    }






}