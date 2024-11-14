package org.sopt.and.network.model.response

import com.google.gson.annotations.SerializedName


data class UserTokenResponse(
    @SerializedName("result")
    val result: UserTokenResultData
)

data class UserTokenResultData(
    @SerializedName("token")
    val token: String
)