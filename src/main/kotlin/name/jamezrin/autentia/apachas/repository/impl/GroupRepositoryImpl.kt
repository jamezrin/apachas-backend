package name.jamezrin.autentia.apachas.repository.impl

import io.micronaut.transaction.annotation.ReadOnly
import jakarta.inject.Singleton
import name.jamezrin.autentia.apachas.domain.Group
import name.jamezrin.autentia.apachas.repository.GroupRepository
import name.jamezrin.autentia.apachas.repository.generic.GenericCrudRepositoryImpl
import javax.persistence.NoResultException

@Singleton
open class GroupRepositoryImpl : GenericCrudRepositoryImpl<Group, Long>(Group::class.java), GroupRepository {
    @ReadOnly
    override fun findByName(name: String): Group? {
        return try {
            val cb = entityManager.criteriaBuilder
            val query = cb.createQuery(Group::class.java)
            val groupEntity = query.from(Group::class.java)
            query
                .select(groupEntity)
                .where(cb.equal(groupEntity.get<String>("name"), name))
            entityManager.createQuery(query).singleResult
        } catch (e: NoResultException) {
            null
        }
    }
}
