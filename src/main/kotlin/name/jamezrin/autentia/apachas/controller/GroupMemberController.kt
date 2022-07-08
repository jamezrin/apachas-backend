package name.jamezrin.autentia.apachas.controller

import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import jakarta.inject.Inject
import name.jamezrin.autentia.apachas.domain.GroupMember
import name.jamezrin.autentia.apachas.exceptions.types.GroupNotFoundException
import name.jamezrin.autentia.apachas.model.CreateGroupMemberRequestBody
import name.jamezrin.autentia.apachas.repository.GroupRepository
import name.jamezrin.autentia.apachas.repository.GroupMemberRepository
import org.slf4j.LoggerFactory

@Controller("/api/groups/{groupName}/members")
class GroupMemberController {
    @Inject
    lateinit var groupRepository: GroupRepository

    @Inject
    lateinit var memberRepository: GroupMemberRepository

    @Get(uri = "/", produces = [MediaType.APPLICATION_JSON])
    fun getGroupMembers(@PathVariable groupName: String): List<GroupMember> {
        val group = groupRepository.findByName(groupName) ?: throw GroupNotFoundException()

        return group.friends
    }

    @Post(uri = "/", produces = [MediaType.APPLICATION_JSON], consumes = [MediaType.APPLICATION_JSON])
    fun createGroupMember(@PathVariable groupName: String, @Body createGroupRequest: CreateGroupMemberRequestBody): HttpStatus {
        val group = groupRepository.findByName(groupName) ?: throw GroupNotFoundException()

        val person = GroupMember(
            name = createGroupRequest.name,
            group = group,
        )

        memberRepository.insert(person)

        return HttpStatus.CREATED
    }

    companion object {
        private val logger = LoggerFactory.getLogger(GroupMemberController::class.java)
    }
}