package name.jamezrin.autentia.apachas.model

import io.micronaut.core.annotation.Introspected
import java.time.LocalDateTime

@Introspected
data class CreateGroupExpenseRequestBody(
    var amount: Double,
    var description: String,
    var expenseDate: LocalDateTime
)
