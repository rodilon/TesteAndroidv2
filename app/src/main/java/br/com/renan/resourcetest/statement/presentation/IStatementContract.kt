package br.com.renan.resourcetest.statement.presentation

import br.com.renan.resourcetest.model.data.StatementListDataResult
import br.com.renan.resourcetest.model.data.UserAccountSuccess

interface IStatementContract  {

    interface View {
        fun populateStatementSuccess(statementDataResult: StatementListDataResult)
    }

    interface Presenter {
        fun bind(view: View)
        fun requestStatementData(userId: Int)
    }
}