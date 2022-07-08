package name.jamezrin.autentia.apachas.model

import io.micronaut.core.annotation.Introspected

@Introspected
class CustomApplicationExceptionBody(
    val type: String,
    val message: String,
)