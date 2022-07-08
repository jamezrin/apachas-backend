package name.jamezrin.autentia.apachas.repository.impl

import io.micronaut.transaction.annotation.ReadOnly
import jakarta.inject.Singleton
import name.jamezrin.autentia.apachas.domain.Group
import name.jamezrin.autentia.apachas.domain.GroupMember
import name.jamezrin.autentia.apachas.repository.GroupMemberRepository
import name.jamezrin.autentia.apachas.repository.generic.GenericCrudRepositoryImpl
import javax.persistence.NoResultException

@Singleton
open class GroupMemberRepositoryImpl : GenericCrudRepositoryImpl<GroupMember, Long>(GroupMember::class.java), GroupMemberRepository {

}
