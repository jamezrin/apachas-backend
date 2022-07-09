package name.jamezrin.autentia.apachas.controller

import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import jakarta.inject.Inject
import name.jamezrin.autentia.apachas.domain.GroupExpense
import name.jamezrin.autentia.apachas.exceptions.types.GroupMemberNotFoundException
import name.jamezrin.autentia.apachas.exceptions.types.GroupNotFoundException
import name.jamezrin.autentia.apachas.model.CreateGroupExpenseRequestBody
import name.jamezrin.autentia.apachas.repository.GroupExpenseRepository
import name.jamezrin.autentia.apachas.repository.GroupMemberRepository
import name.jamezrin.autentia.apachas.repository.GroupRepository
import org.slf4j.LoggerFactory

@Controller("/api/groups/{groupName}/members/{memberId}/expenses")
class GroupExpenseController {
    @Inject
    lateinit var groupRepository: GroupRepository

    @Inject
    lateinit var memberRepository: GroupMemberRepository

    @Inject
    lateinit var expenseRepository: GroupExpenseRepository

    @Get(uri = "/", produces = [MediaType.APPLICATION_JSON])
    fun getGroupMemberExpenses(@PathVariable groupName: String, @PathVariable memberId: Long): List<GroupExpense> {
        val member = memberRepository.findById(memberId)
            ?: throw GroupMemberNotFoundException()

        if (groupName != member.group?.name)
            throw GroupNotFoundException()

        return member.expenses
    }

    @Post(uri = "/", produces = [MediaType.APPLICATION_JSON], consumes = [MediaType.APPLICATION_JSON])
    fun createGroupMemberExpense(@PathVariable groupName: String, @PathVariable memberId: Long,
                           @Body createGroupExpenseRequestBody: CreateGroupExpenseRequestBody): HttpStatus {
        val member = memberRepository.findById(memberId)
            ?: throw GroupMemberNotFoundException()

        if (groupName != member.group?.name)
            throw GroupNotFoundException()

        val expense = GroupExpense(
            description = createGroupExpenseRequestBody.description,
            amount = createGroupExpenseRequestBody.amount,
            expenseAt = createGroupExpenseRequestBody.expenseAt,
            person = member,
        )

        expenseRepository.insert(expense)

        return HttpStatus.CREATED
    }

    companion object {
        private val logger = LoggerFactory.getLogger(GroupExpenseController::class.java)
    }
}
