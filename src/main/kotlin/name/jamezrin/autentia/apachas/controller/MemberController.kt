package name.jamezrin.autentia.apachas.controller

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import jakarta.inject.Inject
import name.jamezrin.autentia.apachas.domain.Member
import name.jamezrin.autentia.apachas.exceptions.InvalidEntityException
import name.jamezrin.autentia.apachas.exceptions.types.GroupNotFoundException
import name.jamezrin.autentia.apachas.exceptions.types.MemberNotFoundException
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

    @Get(uri = "/{memberId}", produces = [MediaType.APPLICATION_JSON])
    fun getGroupMember(@PathVariable groupName: String, @PathVariable memberId: Long): Member {
        val member = memberRepository.findById(memberId)
            ?: throw MemberNotFoundException()

        if (groupName != member.group?.name)
            throw GroupNotFoundException()

        return member;
    }

    @Post(uri = "/", produces = [MediaType.APPLICATION_JSON], consumes = [MediaType.APPLICATION_JSON])
    fun createGroupMember(@PathVariable groupName: String, @Body createGroupRequest: CreateMemberRequestBody): HttpResponse<Member> {
        val group = groupRepository.findByName(groupName)
            ?: throw GroupNotFoundException()

        if (createGroupRequest.name.isBlank()) {
            throw InvalidEntityException()
        }

        val member = Member(
            name = createGroupRequest.name,
            group = group,
        )

        memberRepository.insert(member)

        return HttpResponse.created(member)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(MemberController::class.java)
    }
}
