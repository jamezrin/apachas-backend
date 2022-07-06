package name.jamezrin.autentia.apachas

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.HttpStatus

@Controller("/apachasBackend")
class ApachasBackendController {

    @Get(uri = "/", produces = ["text/plain"])
    fun index(): String {
        return "Example Response"
    }
}