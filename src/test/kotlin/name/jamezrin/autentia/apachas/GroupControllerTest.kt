package name.jamezrin.autentia.apachas

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import name.jamezrin.autentia.apachas.domain.Group
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@MicronautTest
class GroupControllerTest {
    @Inject
    @field:Client("/api")
    lateinit var client: HttpClient

    @Test
    fun newGroupSuccess() {
        val request: HttpRequest<Unit> = HttpRequest.GET("/groups")
        val response = client.toBlocking().retrieve(request, Group::class.java)

        assertNotNull(response)
        assertTrue(response.name.isNotEmpty())
        assertEquals(0, response.friends.size)
    }

    @Test
    fun newGroupAndFetchSuccess() {
        val request1: HttpRequest<Unit> = HttpRequest.GET("/groups")
        val response1 = client.toBlocking().retrieve(request1, Group::class.java)
        assertNotNull(response1)

        val request2: HttpRequest<Unit> = HttpRequest.GET("/groups/${response1.name}")
        val response2 = client.toBlocking().retrieve(request2, Group::class.java)
        assertNotNull(response2)

        assertEquals(response1.id, response2.id)
    }

    @Test
    fun notExistingGroupFailure() {
        val request1: HttpRequest<Unit> = HttpRequest.GET("/groups/some-not-existing-name")

        val exception = assertThrows(HttpClientResponseException::class.java) {
            client.toBlocking().retrieve(request1, Group::class.java)
        }

        assertEquals("Could not find the specified group", exception.message)
        assertEquals(HttpStatus.NOT_FOUND, exception.status)
    }
}