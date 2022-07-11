package name.jamezrin.autentia.apachas.exceptions

import io.micronaut.http.HttpStatus

open class NotFoundEntityException(message: String, reason: CustomApplicationReasonType) :
    CustomApplicationException(message, reason, HttpStatus.NOT_FOUND) {
    constructor() : this(
        "The entity could not be found",
        CustomApplicationReasonType.ENTITY_NOT_FOUND
    )
}
