package name.jamezrin.autentia.apachas

import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.BlockingHttpClient
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import name.jamezrin.autentia.apachas.domain.Group
import name.jamezrin.autentia.apachas.domain.GroupMember
import name.jamezrin.autentia.apachas.model.CreateGroupMemberRequestBody
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@MicronautTest
class GroupExpenseControllerTest {
    @Inject
    @field:Client("/api")
    lateinit var client: HttpClient


}