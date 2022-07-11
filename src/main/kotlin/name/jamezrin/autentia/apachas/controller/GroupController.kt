package name.jamezrin.autentia.apachas.controller

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import jakarta.inject.Inject
import name.jamezrin.autentia.apachas.domain.Group
import name.jamezrin.autentia.apachas.domain.Expense
import name.jamezrin.autentia.apachas.domain.Member
import name.jamezrin.autentia.apachas.exceptions.types.GroupNotFoundException
import name.jamezrin.autentia.apachas.repository.GroupRepository
import name.jamezrin.autentia.apachas.service.GroupIdService
import org.slf4j.LoggerFactory
import java.time.LocalDateTime

@Controller("/api/groups")
class GroupController {
    @Inject
    lateinit var groupRepository: GroupRepository

    @Inject
    lateinit var groupIdService: GroupIdService

    @Get(uri = "/", produces = [MediaType.APPLICATION_JSON])
    fun getNewGroup(): HttpResponse<Group> {
        val groupName = groupIdService.generateNew()
        val group = Group(name = groupName, friends = mutableListOf())
        groupRepository.insert(group)
        return HttpResponse.created(group)
    }

    @Get(uri = "/{groupName}", produces = [MediaType.APPLICATION_JSON])
    fun getExistingGroup(@PathVariable groupName: String): Group {
        return groupRepository.findByName(groupName)
            ?: throw GroupNotFoundException()
    }

    @Get(uri = "/example", produces = [MediaType.APPLICATION_JSON])
    fun getExample(): Group {
        val groupName = groupIdService.generateNew()
        val group = Group(name = groupName)

        val person1 = Member(name = "Francisco Buyo", group = group)

        val expense1 = Expense(
            amount = 100.0,
            description = "Cena",
            expenseAt = LocalDateTime.of(2022, 1,  12, 23, 23),
            person = person1
        )

        person1.expenses.add(expense1)

        val person2 = Member(name = "Alfonso Pérez", group = group)

        val expense2 = Expense(
            amount = 10.0,
            description = "Taxi",
            expenseAt = LocalDateTime.of(2022, 10,  29, 10, 23),
            person = person2
        )

        val expense3 = Expense(
            amount = 53.4,
            description = "Compra",
            expenseAt = LocalDateTime.of(2022, 6,  23, 14, 56),
            person = person2
        )

        person2.expenses.add(expense2)
        person2.expenses.add(expense3)

        val person3 = Member(name = "Raúl González", group = group)
        val person4 = Member(name = "José María Gutiérrez", group = group)

        group.friends.add(person1)
        group.friends.add(person2)
        group.friends.add(person3)
        group.friends.add(person4)

        groupRepository.insert(group)
        return group
    }

    companion object {
        private val logger = LoggerFactory.getLogger(GroupController::class.java)
    }
}
