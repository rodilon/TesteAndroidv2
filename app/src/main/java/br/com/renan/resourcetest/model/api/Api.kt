package br.com.renan.resourcetest.model.api

import br.com.renan.resourcetest.model.data.StatementListDataResult
import br.com.renan.resourcetest.model.data.UserAccountAccess
import br.com.renan.resourcetest.model.data.UserAccountSuccess
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    @GET("statements/{id}")
    fun getStatements(@Path(value = "id") id: Int): Flowable<StatementListDataResult>

    @POST("login")
    fun getAccount(@Body body: UserAccountAccess): Flowable<UserAccountSuccess>
}