package name.jamezrin.autentia.apachas.model

import io.micronaut.core.annotation.Introspected
import java.time.LocalDateTime

@Introspected
data class CreateGroupExpenseRequestBody(
    val amount: Double,
    val description: String,
    val expenseDate: LocalDateTime
)
