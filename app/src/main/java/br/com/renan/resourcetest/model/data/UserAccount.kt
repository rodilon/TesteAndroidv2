package br.com.renan.resourcetest.model.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserAccountDataResult(
    @field:Json(name = "userAccount") val userAccountDataList: List<UserAccountData>
) : Parcelable

@Parcelize
data class UserAccountData(
    @field:Json(name = "userId") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "bankAccount") val bankAccount: Int,
    @field:Json(name = "agency") val agency: String,
    @field:Json(name = "balance") val balance: Float
) : Parcelable

data class UserAccountAccess(
    @field:Json(name = "user") var user: String,
    @field:Json(name = "password") var password: String
)