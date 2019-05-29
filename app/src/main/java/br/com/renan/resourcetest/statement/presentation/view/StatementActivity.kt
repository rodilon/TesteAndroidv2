package br.com.renan.resourcetest.statement.presentation.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.widget.ImageView
import android.widget.TextView
import br.com.renan.resourcetest.statement.presentation.IStatementContract
import br.com.renan.resourcetest.MainActivity
import br.com.renan.resourcetest.R
import br.com.renan.resourcetest.model.data.StatementListData
import br.com.renan.resourcetest.model.data.StatementListDataResult
import br.com.renan.resourcetest.model.data.UserAccountAccess
import br.com.renan.resourcetest.model.data.UserAccountSuccess
import br.com.renan.resourcetest.useraccount.presentation.UserAccountPresenter
import br.com.renan.resourcetest.util.addMask
import br.com.renan.resourcetest.util.formatCurrency
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.statement_activity.*

class StatementActivity : AppCompatActivity(), IStatementContract.View {

    lateinit var logout: ImageView
    lateinit var name: TextView
    lateinit var bankAgency: TextView
    lateinit var balance: TextView

    lateinit var user: String
    lateinit var password: String

    private val userAccountPresenter = UserAccountPresenter()
    private lateinit var statementAdapter: StatementAdapter

    private val listStatement = ArrayList<StatementListData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.statement_activity)

        Hawk.init(this).build()

        getExtrasFromMain()

        bindViews()

        userAccountPresenter.bind(this)

        initView()

        userAccountPresenter.requestUserAccountData(user, password)
        callLogout()
    }

    private fun getExtrasFromMain() {
        user = intent.getStringExtra("user")
        password = intent.getStringExtra("password")
    }

    private fun bindViews() {
        name = this.findViewById(R.id.tv_name)
        bankAgency = this.findViewById(R.id.tv_account_number)
        balance = this.findViewById(R.id.tv_balance_number)
        logout = this.findViewById(R.id.iv_logout)
    }

    private fun callLogout() {
        logout.setOnClickListener {
            backPress()
        }
    }

    private fun backPress() {
        Hawk.put("EncryptedAccess", UserAccountAccess(user, password))
        finish()
        goToMainActivity()
    }

    private fun goToMainActivity() {
        val goToMain = Intent(
            this,
            MainActivity::class.java
        )
        startActivity(goToMain)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        backPress()
    }


    override fun populateStatementSuccess(statementDataResult: StatementListDataResult) {
        listStatement.addAll(statementDataResult.statementListData)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = statementAdapter
        statementAdapter.notifyDataSetChanged()
    }

    private fun initView() {
        statementAdapter = StatementAdapter(listStatement)
    }

    override fun populateUserAccountSuccess(userAccountSuccess: UserAccountSuccess) {
        name.text = userAccountSuccess.userAccount.name
        bankAgency.text = getString(R.string.bankBalance,
            userAccountSuccess.userAccount.bankAccount,
            addMask(userAccountSuccess.userAccount.agency, "##.######-##"))
        balance.text = formatCurrency(userAccountSuccess.userAccount.balance)
        callStatementData()
    }

    private fun callStatementData() {
        userAccountPresenter.requestStatementData()
    }
}