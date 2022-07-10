package name.jamezrin.autentia.apachas.repository.impl

import jakarta.inject.Singleton
import name.jamezrin.autentia.apachas.domain.Expense
import name.jamezrin.autentia.apachas.repository.ExpenseRepository
import name.jamezrin.autentia.apachas.repository.generic.GenericCrudRepositoryImpl

@Singleton
open class ExpenseRepositoryImpl : GenericCrudRepositoryImpl<Expense, Long>(Expense::class.java), ExpenseRepository {

}
