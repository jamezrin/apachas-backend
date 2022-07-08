package name.jamezrin.autentia.apachas.repository.impl

import jakarta.inject.Singleton
import name.jamezrin.autentia.apachas.domain.GroupExpense
import name.jamezrin.autentia.apachas.repository.GroupExpenseRepository
import name.jamezrin.autentia.apachas.repository.generic.GenericCrudRepositoryImpl

@Singleton
open class GroupExpenseRepositoryImpl : GenericCrudRepositoryImpl<GroupExpense, Long>(GroupExpense::class.java), GroupExpenseRepository {

}
