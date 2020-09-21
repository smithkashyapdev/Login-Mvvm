package com.development.loginmvvm.model

import android.util.Patterns


 class LoginUser(var strEmailAddress: String, var strPassword: String) {

    val isEmailValid: Boolean
        get() = Patterns.EMAIL_ADDRESS.matcher(strEmailAddress).matches()
    val isPasswordLengthGreaterThan5: Boolean
        get() = strPassword.length > 5

}