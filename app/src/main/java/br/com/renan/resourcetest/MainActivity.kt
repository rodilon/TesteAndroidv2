package br.com.renan.resourcetest

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import br.com.caelum.stella.validation.CPFValidator
import br.com.caelum.stella.validation.InvalidStateException
import br.com.renan.resourcetest.statement.presentation.view.StatementActivity


class MainActivity : AppCompatActivity() {

    private lateinit var fieldPassword: EditText
    private lateinit var fieldUser: EditText
    private lateinit var buttonLogin: Button
    private var flagPass: Boolean = false
    private var flagUser: Boolean = false
    private var correctPass: String = ""
    private var correctUser: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        fieldUser = this.findViewById(R.id.et_user)
        fieldPassword = this.findViewById(R.id.et_password)
        buttonLogin = this.findViewById(R.id.btn_login)

        validatePassword(fieldPassword)
        validateUser(fieldUser)

        buttonLogin.setOnClickListener {
            if (flagPass && flagUser){
                val intent = Intent(this, StatementActivity::class.java)
                intent.putExtra("user", correctUser)
                intent.putExtra("password", correctPass)
                startActivity(intent)
                fieldUser.text = null
                fieldPassword.text = null
                flagPass = false
            } else {
                Snackbar.make(it, "Usuário ou senha incorreta", Snackbar.LENGTH_LONG).show()
            }
        }
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