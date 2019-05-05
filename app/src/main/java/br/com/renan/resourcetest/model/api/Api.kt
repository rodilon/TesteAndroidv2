package br.com.renan.resourcetest.model.api

import br.com.renan.resourcetest.model.data.StatementListDataResult
import br.com.renan.resourcetest.model.data.UserAccountAccess
import br.com.renan.resourcetest.model.data.UserAccountDataResult
import io.reactivex.Flowable
import retrofit2.http.*

interface Api {

    @GET("statements/{id}")
    fun getStatements(@Path(value = "id") id: Int): Flowable<StatementListDataResult>

    @POST("login")
    fun getAccount(@Body body: UserAccountAccess): Flowable<UserAccountDataResult>
}