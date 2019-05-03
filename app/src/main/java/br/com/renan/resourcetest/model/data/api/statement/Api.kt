package br.com.renan.resourcetest.model.data.api.statement

import br.com.renan.resourcetest.model.data.StatementListData
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("statement/{id}")
    fun getStatements(@Path(value = "id") id: Int): Flowable<StatementListData>
}