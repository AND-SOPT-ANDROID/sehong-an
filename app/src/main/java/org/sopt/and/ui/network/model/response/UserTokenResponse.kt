package org.sopt.and.ui.network.model.response

import com.google.gson.annotations.SerializedName


data class UserTokenResponse(
    @SerializedName("token")
    val token: String
)