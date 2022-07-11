package name.jamezrin.autentia.apachas.exceptions

import io.micronaut.http.HttpStatus

open class InvalidEntityException(message: String, reason: CustomApplicationReasonType) :
    CustomApplicationException(message, reason, HttpStatus.NOT_ACCEPTABLE) {
    constructor() : this(
        "The entity could not pass validation",
        CustomApplicationReasonType.ENTITY_INVALID
    )
}
