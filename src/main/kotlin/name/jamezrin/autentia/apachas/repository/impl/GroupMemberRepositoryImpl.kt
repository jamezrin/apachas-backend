package name.jamezrin.autentia.apachas.repository.impl

import jakarta.inject.Singleton
import name.jamezrin.autentia.apachas.domain.GroupMember
import name.jamezrin.autentia.apachas.repository.GroupMemberRepository
import name.jamezrin.autentia.apachas.repository.generic.GenericCrudRepositoryImpl

@Singleton
open class GroupMemberRepositoryImpl : GenericCrudRepositoryImpl<GroupMember, Long>(GroupMember::class.java), GroupMemberRepository {

}
