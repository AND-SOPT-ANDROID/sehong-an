package org.sopt.and.network.model

open class BaseResponse<out T>(
    val success: Boolean,
    val result: T?,
    val error: ErrorResponse?
)

open class ErrorResponse(
    val code: String = "",
    val message: String = ""
)