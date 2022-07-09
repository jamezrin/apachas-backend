package name.jamezrin.autentia.apachas.controller

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/api")
class HealthCheckController {

    @Get(uri="/", produces=["text/plain"])
    fun index(): String {
        return "OK"
    }
}
