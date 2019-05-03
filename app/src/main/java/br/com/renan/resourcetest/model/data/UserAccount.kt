package br.com.renan.resourcetest.model.data

import com.squareup.moshi.Json

data class UserAccountDataResult(
    @field:Json(name = "userAccount") val userAccountDataList: List<UserAccountData>
)

data class UserAccountData(
    @field:Json(name = "userId") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "bankAccount") val bankAccount: Int,
    @field:Json(name = "agency") val agency: String,
    @field:Json(name = "balance") val balance: Float
)