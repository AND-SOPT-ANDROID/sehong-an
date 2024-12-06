package org.sopt.and.network.model.response

import com.google.gson.annotations.SerializedName

data class UserHobbyResponse(
    @SerializedName("hobby")
    val hobby: String
)