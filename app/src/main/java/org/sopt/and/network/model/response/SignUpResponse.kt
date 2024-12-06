package org.sopt.and.network.model.response

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("no")
    val no: Int
)