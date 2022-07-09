package name.jamezrin.autentia.apachas.repository.generic

import io.micronaut.transaction.annotation.ReadOnly
import jakarta.inject.Inject
import javax.persistence.EntityManager
import javax.transaction.Transactional

open class GenericCrudRepositoryImpl<E, K>(
    private val clazz: Class<E>
) : GenericCrudRepository<E, K> {
    @Inject
    protected lateinit var entityManager: EntityManager

    @ReadOnly
    override fun findAll(): List<E> {
        val cb = entityManager.criteriaBuilder
        val criteria = cb.createQuery(clazz)
        val root = criteria.from(clazz)

        criteria.select(root)

        return entityManager.createQuery(criteria).resultList
    }

    @ReadOnly
    override fun findById(id: K): E? = entityManager.find(clazz, id)

    @Transactional
    override fun delete(entity: E) = entityManager.remove(entity)

    @Transactional
    override fun insert(entity: E) = entityManager.persist(entity)

    @Transactional
    override fun update(entity: E): E = entityManager.merge(entity)
}