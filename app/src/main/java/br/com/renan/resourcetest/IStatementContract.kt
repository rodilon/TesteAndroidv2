package br.com.renan.resourcetest

import br.com.renan.resourcetest.model.data.StatementListDataResult

interface IStatementContract  {

    interface View {
        fun populateStatementSuccess(dataResult: StatementListDataResult)
    }

    interface Presenter {
        fun bind(view: View)
    }
}