package name.jamezrin.autentia.apachas

import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Test

@MicronautTest
class ExpenseControllerTest {
    @Inject
    @field:Client("/api")
    lateinit var client: HttpClient

    @Test
    fun groupNonExistingGetMemberExpensesFailure() {
        TODO()
    }

    @Test
    fun nonExistingGroupGetOtherMemberExpensesFailure() {
        TODO()
    }

    @Test
    fun groupMemberGetNoExpensesSuccess() {
        TODO()
    }

    @Test
    fun groupMemberGetCreatedExpensesSuccess() {
        TODO()
    }

    @Test
    fun groupMemberAddAndGetExpensesSuccess() {
        TODO()
    }
}