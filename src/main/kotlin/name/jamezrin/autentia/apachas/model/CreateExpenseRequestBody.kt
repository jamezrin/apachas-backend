package name.jamezrin.autentia.apachas.model

import io.micronaut.core.annotation.Introspected
import java.time.LocalDateTime

@Introspected
data class CreateExpenseRequestBody(
    val amount: Double,
    val description: String,
    val expenseAt: LocalDateTime
)
