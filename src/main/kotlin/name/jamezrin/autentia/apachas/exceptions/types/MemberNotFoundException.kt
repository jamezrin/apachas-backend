package name.jamezrin.autentia.apachas.exceptions.types

import io.micronaut.http.HttpStatus
import name.jamezrin.autentia.apachas.exceptions.CustomApplicationException
import name.jamezrin.autentia.apachas.exceptions.CustomApplicationReasonType

class MemberNotFoundException : CustomApplicationException(
    "Could not find the specified group member",
    CustomApplicationReasonType.GROUP_MEMBER_NOT_FOUND,
    HttpStatus.NOT_FOUND
)
