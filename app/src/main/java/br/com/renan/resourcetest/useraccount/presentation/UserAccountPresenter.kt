package br.com.renan.resourcetest.useraccount.presentation

import br.com.renan.resourcetest.model.service.UserAccountService
import br.com.renan.resourcetest.provider.ServiceProvides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UserAccountPresenter : IUserAccountContract.Presenter {


    private lateinit var userAccountService: UserAccountService
    private var compositeDisposable: CompositeDisposable? = null

    lateinit var view: IUserAccountContract.View

    override fun bind(view: IUserAccountContract.View) {
        this.view = view
    }

    override fun requestUserAccountData(login: String, password: String){
        userAccountService = ServiceProvides.getAccountService()

        val requestDisposable: Disposable = userAccountService.getData(login, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                view.populateUserAccountSuccess(it)
            },
                {
                    it.stackTrace
                })
        compositeDisposable?.add(requestDisposable)
    }
}
