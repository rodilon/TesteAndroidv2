package br.com.renan.resourcetest.useraccount.presentation

import br.com.renan.resourcetest.statement.presentation.IStatementContract
import br.com.renan.resourcetest.model.service.StatementService
import br.com.renan.resourcetest.model.service.UserAccountService
import br.com.renan.resourcetest.provider.ServiceProvides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UserAccountPresenter : IStatementContract.Presenter {

    private lateinit var statementService: StatementService
    private lateinit var userAccountService: UserAccountService
    private var compositeDisposable: CompositeDisposable? = null
    private var userId: Int = 0

    lateinit var view: IStatementContract.View

    override fun bind(view: IStatementContract.View) {
        this.view = view
    }


    override fun requestUserAccountData(login: String, password: String){
        userAccountService = ServiceProvides.getAccountService()

        val requestDisposable: Disposable = userAccountService.getData(login, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                view.populateUserAccountSuccess(it)
                userId = it.userAccount.id
            },
                {
                    it.stackTrace
                })

        compositeDisposable?.add(requestDisposable)

    }

    override fun requestStatementData(){
        statementService = ServiceProvides.getStatementService()

        val requestDisposable: Disposable = statementService.getData(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                view.populateStatementSuccess(it)

            },
                {
                    it.stackTrace
                })
        compositeDisposable?.add(requestDisposable)
    }
}
