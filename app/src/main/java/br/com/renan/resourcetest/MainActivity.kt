package br.com.renan.resourcetest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import br.com.caelum.stella.validation.CPFValidator
import br.com.caelum.stella.validation.InvalidStateException
import br.com.renan.resourcetest.statement.presentation.presenter.StatementPresenter
import br.com.renan.resourcetest.useraccount.presentation.UserAccountPresenter

class MainActivity : AppCompatActivity() {

    private val statementPresenter = StatementPresenter()
    private val userAccountPresenter = UserAccountPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val login: EditText = this.findViewById(R.id.et_user)
        val password: EditText = this.findViewById(R.id.et_password)

        validateLogin(login)
        validatePassword(password)

        statementPresenter.requestStatementData()
        userAccountPresenter.requestUserAccountData()
    }



    private fun validatePassword(field: EditText) {
        field.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val text = field.text.toString()
                val regex = """^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{3,}$""".toRegex()
                if (!text.matches(regex))
                    field.error = getString(R.string.invalidPassword)
                else
                    field.error = null
            }

        }

    }

    private fun validateLogin(field: EditText) {
        field.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val text = field.text.toString()
                if (text.contains(getString(R.string.at))){
                    if (!validateEMAIL(text))
                        field.error = getString(R.string.invalidEmail)
                    else
                        field.error = null
                } else {
                    if (!validateCPF(text) && text.isNotEmpty())
                        field.error = getString(R.string.invalidCpf)
                    else
                        field.error = null

                }
            }
        }
    }

    private fun validateCPF(text: String) : Boolean {
        try {
            val cpfValidator = CPFValidator()
            cpfValidator.assertValid(text)
        } catch (e: InvalidStateException) {
            return false
        }
        return true
    }

    private fun validateEMAIL(text: String): Boolean {
        return !TextUtils.isEmpty(text) && android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches()
    }
}