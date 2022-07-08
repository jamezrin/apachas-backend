package name.jamezrin.autentia.apachas.exceptions

import io.micronaut.http.HttpStatus

open class CustomApplicationException(
    message: String,
    val type: CustomApplicationReasonType,
    val status: HttpStatus
) : RuntimeException(message)
