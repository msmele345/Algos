package com.algos.mitch.result

enum class ServiceName(val value: String) {
    REDIS("REDIS CACHE"),
    UNKNOWN("UNKNOWN ERROR");

    companion object {
        fun from(s: String) {
            values().find { serviceName -> serviceName.value == s.toUpperCase() }
            ?.let { serviceName -> serviceName } ?: UNKNOWN
        }
    }
}