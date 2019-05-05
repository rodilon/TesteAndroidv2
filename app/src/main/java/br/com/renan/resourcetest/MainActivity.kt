package br.com.renan.resourcetest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.renan.resourcetest.statement.presentation.StatementPresenter
import br.com.renan.resourcetest.useraccount.presentation.UserAccountPresenter

class MainActivity : AppCompatActivity() {

    private val statementPresenter = StatementPresenter()
    private val userAccountPresenter = UserAccountPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        statementPresenter.requestStatementData()
        userAccountPresenter.requestUserAccountData()
    }
}