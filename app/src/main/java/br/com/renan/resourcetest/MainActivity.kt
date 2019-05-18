package br.com.renan.resourcetest

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import br.com.caelum.stella.validation.CPFValidator
import br.com.caelum.stella.validation.InvalidStateException
import br.com.renan.resourcetest.statement.presentation.view.StatementActivity
import br.com.renan.resourcetest.useraccount.presentation.UserAccountPresenter


class MainActivity : AppCompatActivity() {

    private var correctPass: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val fieldUser: EditText = this.findViewById(R.id.et_user)
        val fieldPassword: EditText = this.findViewById(R.id.et_password)
        val buttonLogin: Button = this.findViewById(R.id.btn_login)

        validatePassword(fieldPassword)
        validateUser(fieldUser)

        buttonLogin.setOnClickListener {
            val intent = Intent(this, StatementActivity::class.java)
            intent.putExtra("user", fieldUser.text.toString())
            intent.putExtra("password", fieldPassword.text.toString())
            startActivity(intent)
        }
    }

    private fun validatePassword(field: EditText) {
        field.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val text = s.toString()
                val regex = """^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{3,}$""".toRegex()
                if (!text.matches(regex) && text.isNotEmpty())
                    field.error = getString(R.string.invalidPassword)
                else {
                    field.error = null
                    correctPass = text
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
                    if (!validateEMAIL(text))
                        field.error = getString(R.string.invalidEmail)
                    else
                        field.error = null
                } else {
                    if (!validateCPF(text) && text.isNotEmpty())
                        field.error = getString(R.string.invalidCpf)
                    else{
                        field.error = null
                    }
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