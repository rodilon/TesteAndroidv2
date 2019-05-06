package br.com.renan.resourcetest.useraccount.presentation

import br.com.renan.resourcetest.model.service.UserAccountService
import br.com.renan.resourcetest.provider.ServiceProvides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UserAccountPresenter {
    lateinit var userAccountService: UserAccountService
    lateinit var compositeDisposable: CompositeDisposable


    fun requestUserAccountData(){
        userAccountService = ServiceProvides.getAccountService()


        val requestDisposable: Disposable = userAccountService.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
            },
                {
                    it.stackTrace
                })

        compositeDisposable?.add(requestDisposable)

    }
}
