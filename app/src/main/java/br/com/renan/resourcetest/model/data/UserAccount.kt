package br.com.renan.resourcetest.model.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserAccountSuccess(
    @field:Json(name = "userAccount") val userAccount: UserAccountData
) : Parcelable

@Parcelize
data class UserAccountData(
    @field:Json(name = "userId") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "bankAccount") val bankAccount: String,
    @field:Json(name = "agency") val agency: String,
    @field:Json(name = "balance") val balance: Double
) : Parcelable

data class UserAccountAccess(
    @field:Json(name = "user") var user: String,
    @field:Json(name = "password") var password: String
)