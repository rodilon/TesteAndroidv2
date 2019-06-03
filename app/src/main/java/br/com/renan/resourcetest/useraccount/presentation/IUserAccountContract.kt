package br.com.renan.resourcetest.useraccount.presentation

import br.com.renan.resourcetest.model.data.UserAccountSuccess

interface IUserAccountContract {

    interface View {
        fun populateUserAccountSuccess(userAccountSuccess: UserAccountSuccess)
    }

    interface Presenter {
        fun bind(view: View)
        fun requestUserAccountData(login: String, password: String)
    }
}