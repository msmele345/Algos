package com.algos.mitch.utils

fun String.normalizeRequest(): String {
   return this.split(" ").joinToString(separator = "") { it.capitalize() }.decapitalize()
}