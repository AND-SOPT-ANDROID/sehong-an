package org.sopt.and.domain.entity

data class User(
    val username: String,
    val password: String,
    val hobby: String,
    val token: String? = null
)