package org.sopt.and.data.network.model.response

import com.google.gson.annotations.SerializedName

data class UserHobbyResponse(
    @SerializedName("hobby")
    val hobby: String
)