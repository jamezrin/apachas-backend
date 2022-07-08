package name.jamezrin.autentia.apachas.repository.generic

import io.micronaut.transaction.annotation.ReadOnly
import javax.transaction.Transactional

interface GenericCrudRepository<E, K> {
    @ReadOnly
    fun findAll(): List<E>

    @ReadOnly
    fun findById(id: K): E?

    @Transactional
    fun delete(entity: E)

    @Transactional
    fun insert(entity: E)

    @Transactional
    fun update(entity: E): E
}
