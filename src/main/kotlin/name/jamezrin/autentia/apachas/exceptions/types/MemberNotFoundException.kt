package name.jamezrin.autentia.apachas.exceptions.types

import name.jamezrin.autentia.apachas.exceptions.CustomApplicationReasonType
import name.jamezrin.autentia.apachas.exceptions.NotFoundEntityException

class MemberNotFoundException() : NotFoundEntityException(
    "The specified member could not be found",
    CustomApplicationReasonType.GROUP_MEMBER_NOT_FOUND
)
