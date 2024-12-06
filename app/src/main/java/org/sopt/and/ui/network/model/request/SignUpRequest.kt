package org.sopt.and.ui.network.model.request

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("hobby")
    val hobby: String
)
