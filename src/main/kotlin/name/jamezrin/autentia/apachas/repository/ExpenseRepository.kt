package name.jamezrin.autentia.apachas.repository

import name.jamezrin.autentia.apachas.domain.Expense
import name.jamezrin.autentia.apachas.repository.generic.GenericCrudRepository

interface ExpenseRepository : GenericCrudRepository<Expense, Long> {

}
