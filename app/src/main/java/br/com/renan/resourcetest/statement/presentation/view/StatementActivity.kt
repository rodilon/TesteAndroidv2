package br.com.renan.resourcetest.statement.presentation.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.widget.TextView
import br.com.renan.resourcetest.IStatementContract
import br.com.renan.resourcetest.R
import br.com.renan.resourcetest.model.data.StatementListData
import br.com.renan.resourcetest.model.data.StatementListDataResult
import br.com.renan.resourcetest.model.data.UserAccountSuccess
import br.com.renan.resourcetest.useraccount.presentation.UserAccountPresenter
import br.com.renan.resourcetest.util.addMask
import br.com.renan.resourcetest.util.formatCurrency
import kotlinx.android.synthetic.main.statement_activity.*

class StatementActivity : AppCompatActivity(), IStatementContract.View {

    lateinit var name: TextView
    lateinit var bankAgency: TextView
    lateinit var balance: TextView

    private val userAccountPresenter = UserAccountPresenter()
    private lateinit var statementAdapter: StatementAdapter

    private val listStatement = ArrayList<StatementListData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.statement_activity)

        val user = intent.getStringExtra("user")
        val password = intent.getStringExtra("password")

        name = this.findViewById(R.id.tv_name)
        bankAgency = this.findViewById(R.id.tv_account_number)
        balance = this.findViewById(R.id.tv_balance_number)

        userAccountPresenter.bind(this)

        statementAdapter = StatementAdapter(listStatement)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = statementAdapter


        userAccountPresenter.requestUserAccountData(user, password)
        userAccountPresenter.requestStatementData()
    }


    override fun populateStatementSuccess(statementDataResult: StatementListDataResult) {
        listStatement.addAll(statementDataResult.statementListData)
        statementAdapter.notifyDataSetChanged()
    }

    override fun populateUserAccountSuccess(userAccountSuccess: UserAccountSuccess) {
        name.text = userAccountSuccess.userAccount.name
        bankAgency.text = getString(R.string.bankBalance,
            userAccountSuccess.userAccount.bankAccount,
            addMask(userAccountSuccess.userAccount.agency, "##.######-##"))
        balance.text = formatCurrency(userAccountSuccess.userAccount.balance)
    }
}