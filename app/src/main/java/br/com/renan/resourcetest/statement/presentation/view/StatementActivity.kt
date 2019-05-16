package br.com.renan.resourcetest.statement.presentation.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import br.com.renan.resourcetest.IStatementContract
import br.com.renan.resourcetest.R
import br.com.renan.resourcetest.model.data.StatementListData
import br.com.renan.resourcetest.model.data.StatementListDataResult
import br.com.renan.resourcetest.statement.presentation.presenter.StatementPresenter
import kotlinx.android.synthetic.main.statement_activity.*

class StatementActivity : AppCompatActivity(), IStatementContract.View {

    private val statementPresenter = StatementPresenter()
    private lateinit var statementAdapter: StatementAdapter

    private val listStatement = ArrayList<StatementListData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.statement_activity)

        statementPresenter.bind(this)

        statementAdapter = StatementAdapter(listStatement)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = statementAdapter

        statementPresenter.requestStatementData()
    }


    override fun populateStatementSuccess(dataResult: StatementListDataResult) {
        listStatement.addAll(dataResult.statementListData)
        statementAdapter.notifyDataSetChanged()
    }
}