package br.com.renan.resourcetest.model.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StatementListDataResult(
    @field:Json(name = "statementList") val statementListData: List<StatementListData>
) : Parcelable

@Parcelize
data class StatementListData(
    @field:Json(name = "title") val title: String,
    @field:Json(name = "desc") val description: String,
    @field:Json(name = "date") val date: String,
    @field:Json(name = "value") val value: Double
) : Parcelable