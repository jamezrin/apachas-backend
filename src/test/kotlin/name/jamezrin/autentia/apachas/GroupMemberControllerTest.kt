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
class GroupMemberControllerTest {
    @Inject
    @field:Client("/api")
    lateinit var client: HttpClient

    @Test
    fun newGroupAndAddMemberSuccess() {
        val request1: HttpRequest<Unit> = HttpRequest.GET("/groups")
        val response1 = client.toBlocking().retrieve(request1, Group::class.java)
        assertNotNull(response1)
        assertEquals(0, response1.friends.size)

        val requestBody = CreateGroupMemberRequestBody(name = "Jaime Martínez Rincón")
        val request2: HttpRequest<CreateGroupMemberRequestBody> = HttpRequest.POST("/groups/${response1.name}/members", requestBody)
        val response2 = client.toBlocking().exchange<CreateGroupMemberRequestBody, Any>(request2)
        assertNotNull(response2)
        assertEquals(HttpStatus.CREATED, response2.status)

        val request3: HttpRequest<Unit> = HttpRequest.GET("/groups/${response1.name}")
        val response3 = client.toBlocking().retrieve(request3, Group::class.java)
        assertNotNull(response3)
        assertEquals(1, response3.friends.size)
        assertEquals("Jaime Martínez Rincón", response3.friends.first().name)
    }

    @Test
    fun notExistingGroupAndAddMemberFailure() {
        val requestBody = CreateGroupMemberRequestBody(name = "Jaime Martínez Rincón")
        val request2: HttpRequest<CreateGroupMemberRequestBody> = HttpRequest.POST("/groups/not-existing-group/members", requestBody)

        val exception = assertThrows(HttpClientResponseException::class.java) {
            client.toBlocking().exchange<CreateGroupMemberRequestBody, Any>(request2)
        }

        assertEquals("Could not find the specified group", exception.message)
        assertEquals(HttpStatus.NOT_FOUND, exception.status)
    }

    @Test
    fun newGroupAndGetMembersSuccess() {
        val request1: HttpRequest<Unit> = HttpRequest.GET("/groups")
        val response1 = client.toBlocking().retrieve(request1, Group::class.java)
        assertNotNull(response1)
        assertEquals(0, response1.friends.size)

        val requestBody = CreateGroupMemberRequestBody(name = "Jaime Martínez Rincón")
        val request2: HttpRequest<CreateGroupMemberRequestBody> = HttpRequest.POST("/groups/${response1.name}/members", requestBody)
        val response2 = client.toBlocking().exchange<CreateGroupMemberRequestBody, Any>(request2)
        assertNotNull(response2)
        assertEquals(HttpStatus.CREATED, response2.status)

        val request3: HttpRequest<Unit> = HttpRequest.GET("/groups/${response1.name}/members")
        val response3 = client.toBlocking().retrieve(request3, Argument.listOf(GroupMember::class.java))
        assertNotNull(response3)
        assertEquals(1, response3.size)
        assertEquals("Jaime Martínez Rincón", response3.first().name)
    }

    @Test
    fun notExistingGroupAndGetMembersFailure() {
        val request3: HttpRequest<Unit> = HttpRequest.GET("/groups/not-existing-group/members")

        val exception = assertThrows(HttpClientResponseException::class.java) {
            client.toBlocking().retrieve(request3)
        }

        assertEquals("Could not find the specified group", exception.message)
        assertEquals(HttpStatus.NOT_FOUND, exception.status)
    }
}