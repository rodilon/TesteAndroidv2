package br.com.renan.resourcetest.statement.presentation

import br.com.renan.resourcetest.model.service.StatementService
import br.com.renan.resourcetest.provider.ServiceProvides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class StatementPresenter {
    private lateinit var statementService: StatementService
    private var compositeDisposable: CompositeDisposable? = null

    fun requestStatementData(){
        statementService = ServiceProvides.getStatementService()

        val requestDisposable: Disposable = statementService.getData()
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