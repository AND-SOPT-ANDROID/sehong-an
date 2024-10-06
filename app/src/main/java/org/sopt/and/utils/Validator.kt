package org.sopt.and.utils

import android.util.Patterns

// 이메일 유효성 검사 함수
fun isValidEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

// 비밀번호 유효성 검사 함수 (8~20자 이내로 영문 대소문자, 숫자, 특수문자 중 3가지 이상 혼용하여 입력)
fun isValidPassword(password: String): Boolean {
    val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\$%^&*()_+~`\\-={}|:\";'<>?,./]).{8,20}$"
    return password.matches(Regex(passwordPattern))
}