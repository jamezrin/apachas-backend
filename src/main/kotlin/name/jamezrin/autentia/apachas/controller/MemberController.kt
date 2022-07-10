package name.jamezrin.autentia.apachas.controller

import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import jakarta.inject.Inject
import name.jamezrin.autentia.apachas.domain.Member
import name.jamezrin.autentia.apachas.exceptions.types.GroupNotFoundException
import name.jamezrin.autentia.apachas.model.CreateMemberRequestBody
import name.jamezrin.autentia.apachas.repository.MemberRepository
import name.jamezrin.autentia.apachas.repository.GroupRepository
import org.slf4j.LoggerFactory

@Controller("/api/groups/{groupName}/members")
class MemberController {
    @Inject
    lateinit var groupRepository: GroupRepository

    @Inject
    lateinit var memberRepository: MemberRepository

    @Get(uri = "/", produces = [MediaType.APPLICATION_JSON])
    fun getGroupMembers(@PathVariable groupName: String): List<Member> {
        val group = groupRepository.findByName(groupName)
            ?: throw GroupNotFoundException()

        return group.friends
    }

    @Post(uri = "/", produces = [MediaType.APPLICATION_JSON], consumes = [MediaType.APPLICATION_JSON])
    fun createGroupMember(@PathVariable groupName: String, @Body createGroupRequest: CreateMemberRequestBody): HttpStatus {
        val group = groupRepository.findByName(groupName)
            ?: throw GroupNotFoundException()

        val person = Member(
            name = createGroupRequest.name,
            group = group,
        )

        memberRepository.insert(person)

        return HttpStatus.CREATED
    }

    companion object {
        private val logger = LoggerFactory.getLogger(MemberController::class.java)
    }
}
