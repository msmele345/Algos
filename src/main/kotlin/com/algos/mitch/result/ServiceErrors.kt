package com.algos.mitch.result

data class ServiceError(
    val service: ServiceName = ServiceName.UNKNOWN,
    val errorMessage: String = "",
    val errorType: ErrorType = ErrorType.UNKNOWN_ERROR,
    val reportable: Boolean = true,
    val keyInError: String = ""
) {
    fun generateErrorMessage(message: String): String =
        "${service.value}: $message${if (errorMessage.isNotBlank()) "${System.lineSeparator()} Caused by: $errorMessage" else ""}"

}

data class ServiceErrors(
    val errors: List<ServiceError>
) : List<ServiceError> by errors

fun serviceErrorOf(error: ServiceError): ServiceErrors = ServiceErrors(listOf(error))
