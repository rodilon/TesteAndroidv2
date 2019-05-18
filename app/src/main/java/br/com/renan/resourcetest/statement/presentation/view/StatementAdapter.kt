package br.com.renan.resourcetest.statement.presentation.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.renan.resourcetest.R
import br.com.renan.resourcetest.util.formatNormalDate
import br.com.renan.resourcetest.model.data.StatementListData
import br.com.renan.resourcetest.util.formatCurrency
import kotlinx.android.synthetic.main.item_statement.view.*
import kotlin.collections.ArrayList

class StatementAdapter(dataResult: ArrayList<StatementListData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var recyclerList: ArrayList<StatementListData> = dataResult

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_statement, parent,false)
        )
    }

    override fun getItemCount(): Int {
        return recyclerList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(recyclerList[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(statementData: StatementListData) = with(itemView) {
            tv_title.text = statementData.title
            tv_desc.text = statementData.description
            tv_value.text = formatCurrency(statementData.value)
            tv_date.text = formatNormalDate(statementData.date)
        }
    }

}