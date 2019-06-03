package br.com.renan.resourcetest.statement.presentation.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.widget.ImageView
import android.widget.TextView
import br.com.renan.resourcetest.MainActivity
import br.com.renan.resourcetest.R
import br.com.renan.resourcetest.model.data.StatementListData
import br.com.renan.resourcetest.model.data.StatementListDataResult
import br.com.renan.resourcetest.model.data.UserAccountSuccess
import br.com.renan.resourcetest.statement.presentation.IStatementContract
import br.com.renan.resourcetest.statement.presentation.StatementPresenter
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
    lateinit var userAccountSuccess: UserAccountSuccess

    private val statementPresenter = StatementPresenter()
    private lateinit var statementAdapter: StatementAdapter

    private val listStatement = ArrayList<StatementListData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.statement_activity)

        getExtrasFromMain()

        bindViews()

        initView()

        statementPresenter.bind(this)

        populateAccountData()

        statementPresenter.requestStatementData(userAccountSuccess.userAccount.id)

        initListners()
    }

    private fun getExtrasFromMain() {
        userAccountSuccess = intent.getParcelableExtra("userAccountSuccess")
    }

    private fun bindViews() {
        name = this.findViewById(R.id.tv_name)
        bankAgency = this.findViewById(R.id.tv_account_number)
        balance = this.findViewById(R.id.tv_balance_number)
        logout = this.findViewById(R.id.iv_logout)
    }

    private fun initListners() {
        logout.setOnClickListener {
            backPress()
        }
    }

    private fun backPress() {
       if (isCached())
           Hawk.delete("EncryptedAccess")
        finish()
        goToMainActivity()
    }

    private fun isCached(): Boolean {
        return Hawk.contains("EncryptedAccess")
    }

    private fun goToMainActivity() {
        val goToMain = Intent(
            this,
            MainActivity::class.java
        )
        startActivity(goToMain)
    }

    override fun onBackPressed() {
        backPress()
        super.onBackPressed()
    }

    private fun populateAccountData() {
        name.text = userAccountSuccess.userAccount.name
        bankAgency.text = getString(R.string.bankBalance,
            userAccountSuccess.userAccount.bankAccount,
            addMask(userAccountSuccess.userAccount.agency, "##.######-##"))
        balance.text = formatCurrency(userAccountSuccess.userAccount.balance)
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
}