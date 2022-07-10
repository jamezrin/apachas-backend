package name.jamezrin.autentia.apachas.repository

import name.jamezrin.autentia.apachas.domain.Member
import name.jamezrin.autentia.apachas.repository.generic.GenericCrudRepository

interface MemberRepository : GenericCrudRepository<Member, Long> {

}