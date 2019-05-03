package br.com.renan.resourcetest

import android.app.Application
import br.com.renan.resourcetest.provider.NetworkProvider

class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        NetworkProvider.init()
    }
}