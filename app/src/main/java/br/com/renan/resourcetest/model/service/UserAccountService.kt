package br.com.renan.resourcetest.model.service

import br.com.renan.resourcetest.model.api.Api
import br.com.renan.resourcetest.model.data.UserAccountAccess
import br.com.renan.resourcetest.model.data.UserAccountDataResult
import br.com.renan.resourcetest.provider.NetworkProvider
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserAccountService {

    private val user: String = "test_user"
    private val pass: String = "Test@1"

    lateinit var api: Api
    private val userAccess: UserAccountAccess = UserAccountAccess(user, pass)

    fun getData(): Flowable<UserAccountDataResult> {
        api = NetworkProvider.getApi()!!

        return api.getAccount(userAccess)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}