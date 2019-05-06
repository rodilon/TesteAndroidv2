package br.com.renan.resourcetest.model.service

import br.com.renan.resourcetest.model.data.StatementListDataResult
import br.com.renan.resourcetest.provider.NetworkProvider
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class StatementService {

    fun getData(): Flowable<StatementListDataResult> {
        return NetworkProvider.getApi()
            .getStatements(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}