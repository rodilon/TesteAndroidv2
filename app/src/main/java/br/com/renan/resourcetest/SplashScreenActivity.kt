package br.com.renan.resourcetest

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import br.com.renan.resourcetest.model.data.UserAccountAccess
import com.orhanobut.hawk.Hawk
import com.orhanobut.hawk.Hawk.get


class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_splash_screen)

        Handler().postDelayed({
            showLogin()
        }, 2000)
    }

    private fun showLogin() {
        if (isCached()) {
            val access = get<UserAccountAccess>("EncryptedAccess")
            goToMainActivity(access)
            Hawk.delete("EncryptedAccess")
        } else {
            goToMainActivity()
        }
        finish()
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun goToMainActivity(access: UserAccountAccess) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("user", access.user)
        intent.putExtra("password", access.password)
        startActivity(intent)
    }

    private fun isCached(): Boolean {
        return Hawk.contains("EncryptedAccess")
    }
}
