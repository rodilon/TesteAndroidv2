package br.com.renan.resourcetest.model.service

import br.com.renan.resourcetest.model.data.UserAccountAccess
import br.com.renan.resourcetest.model.data.UserAccountSuccess
import br.com.renan.resourcetest.provider.NetworkProvider
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserAccountService {
    fun getData(login: String, password: String): Flowable<UserAccountSuccess> {
        val userAccess = UserAccountAccess(login, password)
        return NetworkProvider.getApi().getAccount(userAccess)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}