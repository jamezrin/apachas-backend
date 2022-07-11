package name.jamezrin.autentia.apachas.exceptions.types

import name.jamezrin.autentia.apachas.exceptions.CustomApplicationReasonType
import name.jamezrin.autentia.apachas.exceptions.NotFoundEntityException

class GroupNotFoundException() : NotFoundEntityException(
    "The specified group could not be found",
    CustomApplicationReasonType.GROUP_NOT_FOUND
)