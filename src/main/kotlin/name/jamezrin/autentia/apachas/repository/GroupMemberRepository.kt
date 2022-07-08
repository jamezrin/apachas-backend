package name.jamezrin.autentia.apachas.repository

import name.jamezrin.autentia.apachas.domain.GroupMember
import name.jamezrin.autentia.apachas.repository.generic.GenericCrudRepository

interface GroupMemberRepository : GenericCrudRepository<GroupMember, Long> {

}