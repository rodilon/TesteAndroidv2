package br.com.renan.resourcetest.model.service

import br.com.renan.resourcetest.model.data.StatementListDataResult
import br.com.renan.resourcetest.provider.NetworkProvider
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class StatementService {

    fun getData(userId: Int): Flowable<StatementListDataResult> {
        return NetworkProvider.getApi()
            .getStatements(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}