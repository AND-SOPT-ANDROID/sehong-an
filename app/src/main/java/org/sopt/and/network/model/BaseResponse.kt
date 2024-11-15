package org.sopt.and.network.model

open class BaseResponse<out T>(
    val result: T?,
)

open class ErrorResponse(
    val code: String = "",
)