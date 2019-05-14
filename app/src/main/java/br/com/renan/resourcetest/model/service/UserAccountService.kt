package br.com.renan.resourcetest.model.service

import br.com.renan.resourcetest.model.data.UserAccountAccess
import br.com.renan.resourcetest.model.data.UserAccountDataResult
import br.com.renan.resourcetest.provider.NetworkProvider
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserAccountService {

    private val user: String = "test_user"
    private val pass: String = "Test@1"


    fun getData(login: String, password: String): Flowable<UserAccountDataResult> {
        val userAccess = UserAccountAccess(login, password)
        return NetworkProvider.getApi().getAccount(userAccess)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}