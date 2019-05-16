package br.com.renan.resourcetest.statement.presentation.presenter

import br.com.renan.resourcetest.IStatementContract
import br.com.renan.resourcetest.model.service.StatementService
import br.com.renan.resourcetest.provider.ServiceProvides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class StatementPresenter : IStatementContract.Presenter {
    private lateinit var statementService: StatementService
    private var compositeDisposable: CompositeDisposable? = null

    lateinit var view: IStatementContract.View

    override fun bind(view: IStatementContract.View) {
        this.view = view
    }

    fun requestStatementData(){
        statementService = ServiceProvides.getStatementService()

        val requestDisposable: Disposable = statementService.getData()
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