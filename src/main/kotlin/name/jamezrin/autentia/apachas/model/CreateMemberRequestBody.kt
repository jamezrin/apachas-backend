package name.jamezrin.autentia.apachas.model

import io.micronaut.core.annotation.Introspected

@Introspected
data class CreateMemberRequestBody(
    val name: String
)