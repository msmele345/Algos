package com.algos.mitch.result

enum class ServiceName(val value: String) {
    REDIS("REDIS CACHE"),
    UNKNOWN("UNKNOWN ERROR"),
    MONGO_DB("MONGO DB");

    companion object {
        fun from(s: String) {
            values().find { serviceName -> serviceName.value == s.toUpperCase() }
            ?.let { serviceName -> serviceName } ?: UNKNOWN
        }
    }
}