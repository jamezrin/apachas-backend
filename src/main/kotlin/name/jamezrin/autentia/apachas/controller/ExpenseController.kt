package name.jamezrin.autentia.apachas.controller

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import jakarta.inject.Inject
import name.jamezrin.autentia.apachas.domain.Expense
import name.jamezrin.autentia.apachas.exceptions.InvalidEntityException
import name.jamezrin.autentia.apachas.exceptions.types.MemberNotFoundException
import name.jamezrin.autentia.apachas.exceptions.types.GroupNotFoundException
import name.jamezrin.autentia.apachas.model.CreateExpenseRequestBody
import name.jamezrin.autentia.apachas.repository.ExpenseRepository
import name.jamezrin.autentia.apachas.repository.MemberRepository
import name.jamezrin.autentia.apachas.repository.GroupRepository
import org.slf4j.LoggerFactory

@Controller("/api/groups/{groupName}/members/{memberId}/expenses")
class ExpenseController {
    @Inject
    lateinit var groupRepository: GroupRepository

    @Inject
    lateinit var memberRepository: MemberRepository

    @Inject
    lateinit var expenseRepository: ExpenseRepository

    @Get(uri = "/", produces = [MediaType.APPLICATION_JSON])
    fun getGroupMemberExpenses(@PathVariable groupName: String, @PathVariable memberId: Long): List<Expense> {
        val member = memberRepository.findById(memberId)
            ?: throw MemberNotFoundException()

        if (groupName != member.group?.name)
            throw GroupNotFoundException()

        return member.expenses
    }

    @Post(uri = "/", produces = [MediaType.APPLICATION_JSON], consumes = [MediaType.APPLICATION_JSON])
    fun createGroupMemberExpense(@PathVariable groupName: String, @PathVariable memberId: Long,
                                 @Body createExpenseRequestBody: CreateExpenseRequestBody): HttpResponse<Expense> {
        val member = memberRepository.findById(memberId)
            ?: throw MemberNotFoundException()

        if (groupName != member.group?.name)
            throw GroupNotFoundException()

        if (createExpenseRequestBody.description.isBlank() || createExpenseRequestBody.amount <= 0) {
            throw InvalidEntityException()
        }

        val expense = Expense(
            description = createExpenseRequestBody.description,
            amount = createExpenseRequestBody.amount,
            expenseAt = createExpenseRequestBody.expenseAt,
            person = member,
        )

        expenseRepository.insert(expense)

        return HttpResponse.created(expense)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ExpenseController::class.java)
    }
}
