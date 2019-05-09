package br.com.renan.resourcetest

import android.app.Application
import br.com.renan.resourcetest.provider.NetworkProvider
import uk.co.chrisjenx.calligraphy.CalligraphyConfig



class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
        setFont()
    }

    private fun setFont() {
        CalligraphyConfig.initDefault(
            CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/HelveticaNeue.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }

    private fun init() {
        NetworkProvider.init()
    }
}