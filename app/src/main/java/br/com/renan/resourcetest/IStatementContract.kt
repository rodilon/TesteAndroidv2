package br.com.renan.resourcetest

import br.com.renan.resourcetest.model.data.StatementListDataResult
import br.com.renan.resourcetest.model.data.UserAccountSuccess

interface IStatementContract  {

    interface View {
        fun populateStatementSuccess(statementDataResult: StatementListDataResult)
        fun populateUserAccountSuccess(userAccountSuccess: UserAccountSuccess)
    }

    interface Presenter {
        fun bind(view: View)
        fun requestUserAccountData(login: String, password: String)
        fun requestStatementData()
    }
}