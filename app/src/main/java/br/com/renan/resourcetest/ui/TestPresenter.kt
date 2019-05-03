package br.com.renan.resourcetest.ui

import br.com.renan.resourcetest.provider.ServiceProvides
import br.com.renan.resourcetest.model.service.StatementService

class TestPresenter {

    private var statementService: StatementService? = null

    fun bind() {
        statementService = ServiceProvides.getStatementService()
    }
}