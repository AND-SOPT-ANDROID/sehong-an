package org.sopt.and.network.model

import com.google.gson.annotations.SerializedName

data class BaseResponse<out T>(
    @SerializedName("result")
    val result: T?,
)

data class ErrorResponse(
    val code: String = "",
    val message: String = ""
)