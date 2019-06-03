package br.com.renan.resourcetest

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import br.com.renan.resourcetest.model.data.UserAccountAccess
import br.com.renan.resourcetest.model.data.UserAccountSuccess
import br.com.renan.resourcetest.statement.presentation.view.StatementActivity
import br.com.renan.resourcetest.useraccount.presentation.IUserAccountContract
import br.com.renan.resourcetest.useraccount.presentation.UserAccountPresenter
import br.com.renan.resourcetest.util.validateCPF
import br.com.renan.resourcetest.util.validateEMAIL
import com.orhanobut.hawk.Hawk


class MainActivity : AppCompatActivity(), IUserAccountContract.View {

    private var userAccountAccess: UserAccountAccess? = null
    private val userAccountPresenter = UserAccountPresenter()
    private lateinit var fieldPassword: EditText
    private lateinit var fieldUser: EditText
    private lateinit var buttonLogin: Button
    private var flagPass: Boolean = false
    private var flagUser: Boolean = false
    private var correctPass: String = ""
    private var correctUser: String = ""

    private var userAccountDataSuccess: UserAccountSuccess? = null

    private fun bindViews() {
        fieldUser = this.findViewById(R.id.et_user)
        fieldPassword = this.findViewById(R.id.et_password)
        buttonLogin = this.findViewById(R.id.btn_login)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        bindViews()

        validateLogin()

        userAccountPresenter.bind(this)

        initListners()

        loginWithCache()
    }

    private fun loginWithCache() {
        val user = intent.getStringExtra("user")
        val password = intent.getStringExtra("password")

        if (user != null && password != null) {
            userAccountPresenter.requestUserAccountData(user, password)
        }
    }

    private fun initListners() {
        buttonLogin.setOnClickListener {
            if (flagPass && flagUser) {
                loginWithoutCache()
            } else {
                Snackbar.make(it, "Usuário ou senha incorreta", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun loginWithoutCache() {
        userAccountPresenter.requestUserAccountData(correctUser, correctPass)
    }

    override fun populateUserAccountSuccess(userAccountSuccess: UserAccountSuccess) {
        userAccountDataSuccess = userAccountSuccess
        goToStatementActivity()
    }

    private fun goToStatementActivity() {
        val intent = Intent(this, StatementActivity::class.java)
        intent.putExtra("userAccountSuccess", userAccountDataSuccess)
        startActivity(intent)

        userAccountAccess = UserAccountAccess(correctUser, correctPass)

        Hawk.put("EncryptedAccess", userAccountAccess)
        fieldUser.text = null
        fieldPassword.text = null
        flagPass = false
    }

    private fun validateLogin() {
        validatePassword(fieldPassword)
        validateUser(fieldUser)
    }

    private fun validatePassword(field: EditText) {
        field.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val text = s.toString()
                val regex = """^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{3,}$""".toRegex()
                if (!text.matches(regex) && text.isNotEmpty()) {
                    field.error = getString(R.string.invalidPassword)
                    flagPass = false
                }
                else {
                    field.error = null
                    correctPass = text
                    flagPass = true
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }

    private fun validateUser(field: EditText) {
        field.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val text = field.text.toString()
                if (text.contains(getString(R.string.at))){
                    if (!validateEMAIL(text)) {
                        field.error = getString(R.string.invalidEmail)
                        flagUser = false
                    }
                    else {
                        field.error = null
                        correctUser = text
                        flagUser = true
                    }
                } else {
                    if (!validateCPF(text) && text.isNotEmpty()) {
                        field.error = getString(R.string.invalidCpf)
                        flagUser = false
                    }
                    else{
                        field.error = null
                        correctUser = text
                        flagUser = true
                    }
                }
            }
        }
    }
}