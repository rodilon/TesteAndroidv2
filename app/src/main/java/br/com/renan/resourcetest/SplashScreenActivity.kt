package br.com.renan.resourcetest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.content.Intent
import br.com.renan.resourcetest.model.data.UserAccountAccess
import br.com.renan.resourcetest.statement.presentation.view.StatementActivity
import com.orhanobut.hawk.Hawk
import com.orhanobut.hawk.Hawk.get


class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_splash_screen)

        Hawk.init(this).build()

        Handler().postDelayed({
            mostrarLogin()
        }, 2000)
    }

    private fun mostrarLogin() {

        if (isHawked()) {
            val access = get<UserAccountAccess>("EncryptedAccess")
            val intent = Intent(this, StatementActivity::class.java)
            intent.putExtra("user", access.user)
            intent.putExtra("password", access.password)
            startActivity(intent)
            Hawk.destroy()
        } else {
            val intent = Intent(
                this,
                MainActivity::class.java
            )
            startActivity(intent)
        }
        finish()
    }

    private fun isHawked(): Boolean {
        return Hawk.contains("EncryptedAccess")
    }
}
