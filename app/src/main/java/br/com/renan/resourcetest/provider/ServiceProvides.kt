package br.com.renan.resourcetest.provider

import br.com.renan.resourcetest.model.service.StatementService
import br.com.renan.resourcetest.model.service.UserAccountService

object ServiceProvides {

    private var statementService: StatementService? = null
    private var userAccountService: UserAccountService? = null

    fun getStatementService() = statementService ?: StatementService()
    fun getAccountService() = userAccountService ?: UserAccountService()
}