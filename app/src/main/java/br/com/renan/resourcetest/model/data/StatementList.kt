package br.com.renan.resourcetest.model.data

import com.squareup.moshi.Json

data class StatementListDataResult(
    @field:Json(name = "statementList") val statementListData: List<StatementListData>
)

data class StatementListData(
    @field:Json(name = "title") val title: String,
    @field:Json(name = "desc") val description: String,
    @field:Json(name = "date") val date: String,
    @field:Json(name = "value") val value: Double
)