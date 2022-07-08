package name.jamezrin.autentia.apachas.configuration.exceptions

import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler
import jakarta.inject.Singleton
import name.jamezrin.autentia.apachas.exceptions.CustomApplicationException
import name.jamezrin.autentia.apachas.model.CustomApplicationExceptionBody


@Produces
@Singleton
@Requires(classes = [CustomApplicationException::class, ExceptionHandler::class])
class CustomApplicationExceptionHandler : ExceptionHandler<CustomApplicationException, HttpResponse<CustomApplicationExceptionBody>> {
    override fun handle(request: HttpRequest<*>?, exception: CustomApplicationException): HttpResponse<CustomApplicationExceptionBody> {
        return HttpResponse
            .status<CustomApplicationExceptionBody?>(exception.status)
            .body(CustomApplicationExceptionBody(type = exception.type.name, message = exception.message!!))
    }
}
