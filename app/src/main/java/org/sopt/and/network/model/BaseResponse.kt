package org.sopt.and.network.model

import com.google.gson.annotations.SerializedName

open class BaseResponse<out T>(
    @SerializedName("result")
    val result: T?,
)

open class ErrorResponse(
    val code: String = "",
    val message: String = ""
)