package br.com.renan.resourcetest.provider

import br.com.renan.resourcetest.model.service.StatementService

object ServiceProvides {

    private var statementService: StatementService? = null

    fun getStatementService() = statementService ?: StatementService()
}