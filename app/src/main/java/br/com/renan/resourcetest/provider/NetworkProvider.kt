package br.com.renan.resourcetest.provider

import br.com.renan.resourcetest.BuildConfig
import br.com.renan.resourcetest.model.data.api.statement.Api
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkProvider {

    private var okHttpBuilder: OkHttpClient.Builder? = null
    private var api: Api? = null

    fun init() {
        provideOkHttpBuilder()
        provideApi()
    }

    private fun provideOkHttpBuilder() {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        okHttpBuilder = builder
    }

    private fun provideApi() {
        api = Retrofit.Builder()
            .client(okHttpBuilder?.build()!!)
            .baseUrl(BuildConfig.API)
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .build()
            .create(Api::class.java)
    }

    fun getApi() = api
}