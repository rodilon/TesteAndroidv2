package br.com.renan.resourcetest.model.service

import br.com.renan.resourcetest.model.data.api.statement.Api
import br.com.renan.resourcetest.provider.NetworkProvider

class StatementService {

    private var api: Api? = null

    fun bind() {
        api = NetworkProvider.getApi()
    }

    fun getData() {
        api?.getStatements(1)
    }
}