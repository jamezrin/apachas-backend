package name.jamezrin.autentia.apachas

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import name.jamezrin.autentia.apachas.domain.Expense
import name.jamezrin.autentia.apachas.domain.Group
import name.jamezrin.autentia.apachas.domain.Member
import name.jamezrin.autentia.apachas.model.CreateExpenseRequestBody
import name.jamezrin.autentia.apachas.model.CreateMemberRequestBody
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

@MicronautTest
class ExpenseControllerTest {
    @Inject
    @field:Client("/api")
    lateinit var client: HttpClient

    @Test
    fun groupNonExistingGetMemberExpensesFailure() {
        val request2: HttpRequest<CreateMemberRequestBody> = HttpRequest.GET("/groups/some-random-name/members/99999999/expenses")

        val exception = assertThrows(HttpClientResponseException::class.java) {
            client.toBlocking().retrieve(request2)
        }

        assertEquals("The specified member could not be found", exception.message)
        assertEquals(HttpStatus.NOT_FOUND, exception.status)
    }

    @Test
    fun nonExistingGroupGetOtherMemberExpensesFailure() {
        val request1: HttpRequest<Unit> = HttpRequest.GET("/groups")
        val response1 = client.toBlocking().retrieve(request1, Group::class.java)
        assertNotNull(response1)
        assertEquals(0, response1.friends.size)

        val requestBody = CreateMemberRequestBody(name = "Jaime Martínez Rincón")
        val request2: HttpRequest<CreateMemberRequestBody> = HttpRequest.POST("/groups/${response1.name}/members", requestBody)
        val response2 = client.toBlocking().exchange(request2, Member::class.java)

        assertEquals(HttpStatus.CREATED, response2.status)
        assertNotNull(response2.body())
        assertEquals("Jaime Martínez Rincón", response2.body()!!.name)

        val request3: HttpRequest<CreateMemberRequestBody> = HttpRequest.GET("/groups/some-random-name/members/${response2.body()!!.id}/expenses")

        val exception = assertThrows(HttpClientResponseException::class.java) {
            client.toBlocking().retrieve(request3)
        }

        assertEquals("The specified group could not be found", exception.message)
        assertEquals(HttpStatus.NOT_FOUND, exception.status)
    }

    @Test
    fun groupMemberGetNoExpensesSuccess() {
        val request1: HttpRequest<Unit> = HttpRequest.GET("/groups")
        val response1 = client.toBlocking().retrieve(request1, Group::class.java)
        assertNotNull(response1)
        assertEquals(0, response1.friends.size)

        val requestBody = CreateMemberRequestBody(name = "Jaime Martínez Rincón")
        val request2: HttpRequest<CreateMemberRequestBody> = HttpRequest.POST("/groups/${response1.name}/members", requestBody)
        val response2 = client.toBlocking().exchange(request2, Member::class.java)

        assertEquals(HttpStatus.CREATED, response2.status)
        assertNotNull(response2.body())
        assertEquals("Jaime Martínez Rincón", response2.body()!!.name)

        assertTrue(response2.body()!!.expenses.isEmpty())
    }

    @Test
    fun groupMemberAddExpenseSuccess() {
        val request1: HttpRequest<Unit> = HttpRequest.GET("/groups")
        val response1 = client.toBlocking().retrieve(request1, Group::class.java)
        assertNotNull(response1)
        assertEquals(0, response1.friends.size)

        val requestBody = CreateMemberRequestBody(name = "Jaime Martínez Rincón")
        val request2: HttpRequest<CreateMemberRequestBody> = HttpRequest.POST("/groups/${response1.name}/members", requestBody)
        val response2 = client.toBlocking().exchange(request2, Member::class.java)

        val requestBody2 = CreateExpenseRequestBody(amount = 1337.0, description = "test", expenseAt = LocalDateTime.now())
        val request3: HttpRequest<CreateExpenseRequestBody> = HttpRequest.POST("/groups/${response1.name}/members/${response2.body()!!.id}/expenses", requestBody2)
        val response3 = client.toBlocking().exchange(request3, Expense::class.java)

        assertEquals(HttpStatus.CREATED, response3.status)
        assertNotNull(response3.body())
        assertEquals("test", response3.body()!!.description)
    }

    @Test
    fun groupMemberAddAndGetGroupExpensesSuccess() {
        val request1: HttpRequest<Unit> = HttpRequest.GET("/groups")
        val response1 = client.toBlocking().retrieve(request1, Group::class.java)
        assertNotNull(response1)
        assertEquals(0, response1.friends.size)

        val requestBody = CreateMemberRequestBody(name = "Jaime Martínez Rincón")
        val request2: HttpRequest<CreateMemberRequestBody> = HttpRequest.POST("/groups/${response1.name}/members", requestBody)
        val response2 = client.toBlocking().exchange(request2, Member::class.java)

        val requestBody2 = CreateExpenseRequestBody(amount = 1337.0, description = "test", expenseAt = LocalDateTime.now())
        val request3: HttpRequest<CreateExpenseRequestBody> = HttpRequest.POST("/groups/${response1.name}/members/${response2.body()!!.id}/expenses", requestBody2)
        val response3 = client.toBlocking().exchange(request3, Expense::class.java)

        assertEquals(HttpStatus.CREATED, response3.status)

        val request4: HttpRequest<Unit> = HttpRequest.GET("/groups/${response1.name}")
        val response4 = client.toBlocking().retrieve(request4, Group::class.java)
        assertNotNull(response4)

        assertEquals(1, response4.friends.size)
        assertEquals(1, response4.friends[0].expenses.size)
        assertEquals("test", response4.friends[0].expenses[0].description)
    }

    @Test
    fun groupMemberAddAndGetMemberExpensesSuccess() {
        val request1: HttpRequest<Unit> = HttpRequest.GET("/groups")
        val response1 = client.toBlocking().retrieve(request1, Group::class.java)
        assertNotNull(response1)
        assertEquals(0, response1.friends.size)

        val requestBody = CreateMemberRequestBody(name = "Jaime Martínez Rincón")
        val request2: HttpRequest<CreateMemberRequestBody> = HttpRequest.POST("/groups/${response1.name}/members", requestBody)
        val response2 = client.toBlocking().exchange(request2, Member::class.java)

        val requestBody2 = CreateExpenseRequestBody(amount = 1337.0, description = "test", expenseAt = LocalDateTime.now())
        val request3: HttpRequest<CreateExpenseRequestBody> = HttpRequest.POST("/groups/${response1.name}/members/${response2.body()!!.id}/expenses", requestBody2)
        val response3 = client.toBlocking().exchange(request3, Expense::class.java)

        assertEquals(HttpStatus.CREATED, response3.status)

        val request4: HttpRequest<Unit> = HttpRequest.GET("/groups/${response1.name}/members/${response2.body()!!.id}")
        val response4 = client.toBlocking().retrieve(request4, Member::class.java)
        assertNotNull(response4)

        assertEquals(1, response4.expenses.size)
        assertEquals("test", response4.expenses[0].description)
    }
}