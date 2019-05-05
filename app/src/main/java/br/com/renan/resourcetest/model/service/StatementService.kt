package br.com.renan.resourcetest.model.service

import br.com.renan.resourcetest.model.api.Api
import br.com.renan.resourcetest.model.data.StatementListDataResult
import br.com.renan.resourcetest.provider.NetworkProvider
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class StatementService {

    lateinit var api: Api

    fun getData(): Flowable<StatementListDataResult> {
        api = NetworkProvider.getApi()!!

        return api.getStatements(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}