package com.example.soplant.domain.utils

import android.util.Patterns
import javax.inject.Inject

class StringValidators @Inject constructor() {
    fun validatePasswordFormat(passwordToValidate: String): Boolean {
        return """^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#${'$'}%!\-_?&])(?=\S+${'$'}).{8,}""".toRegex().matches(passwordToValidate)
    }

    fun validateEmailFormat(emailToValidate: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(emailToValidate).matches()
    }
}