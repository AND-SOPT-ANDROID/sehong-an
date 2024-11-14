package org.sopt.and.network.model.response

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("result")
    val result: SignUpResultData
)

data class SignUpResultData(
    @SerializedName("no")
    val no: Int
)