package org.sopt.and.data.network.model.response

import com.google.gson.annotations.SerializedName


data class UserTokenResponse(
    @SerializedName("token")
    val token: String
)