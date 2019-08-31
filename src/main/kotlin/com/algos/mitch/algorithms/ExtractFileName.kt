package com.algos.mitch.algorithms

class ExtractFileName {

    fun extractFileName(file: String): String {
        val (fileName, fileType) = file.split(".").dropLast(1)
        return removeDate(fileName).let { actualFileName ->
            "$actualFileName.$fileType"
        }
    }

    fun removeDate(fileName: String): String = fileName.split("_").drop(1).joinToString("_")
}

// drop removes the n number of elements from the front and returns that remaining pieces
// take removes the n number of elements at the front and returns them specifically

//fun removeDate(fileName: String): String {
//    return ("0".."9").let { range ->
//        fileName.filter { it.toString() !in range }.drop(1)
//    }
//}
//

//ALTERNATIVES TO STUDY:
//fun extractFileName(dirtyFileName:String): String {
//    return dirtyFileName.substringAfter("_").substringBeforeLast(".")
//
//}
//
//fun extractFileName(dirtyFile:String):String {
//    return dirtyFile
//            .replaceBefore("_", "")
//            .replaceAfterLast(".", "")
//            .removeSurrounding("_",".")
//}