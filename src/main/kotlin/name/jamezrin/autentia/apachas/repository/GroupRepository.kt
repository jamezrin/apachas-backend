package name.jamezrin.autentia.apachas.repository

import name.jamezrin.autentia.apachas.domain.Group
import name.jamezrin.autentia.apachas.repository.generic.GenericCrudRepository

interface GroupRepository : GenericCrudRepository<Group, Long> {
    fun findByName(name: String): Group?
}