package name.jamezrin.autentia.apachas.repository.impl

import jakarta.inject.Singleton
import name.jamezrin.autentia.apachas.domain.Member
import name.jamezrin.autentia.apachas.repository.MemberRepository
import name.jamezrin.autentia.apachas.repository.generic.GenericCrudRepositoryImpl

@Singleton
open class MemberRepositoryImpl : GenericCrudRepositoryImpl<Member, Long>(Member::class.java), MemberRepository {

}
