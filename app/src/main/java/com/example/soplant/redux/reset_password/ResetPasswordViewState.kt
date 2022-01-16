package com.example.soplant.redux.reset_password

import com.example.soplant.redux.State
import javax.inject.Inject

data class ResetPasswordViewState(
    val email: String,
    val isVerifyingUser: Boolean,
    val errorCode: String,
    val canProcessNextStep: Boolean,
    val isValidEmail: Boolean,
    val mustVerifyUser: Boolean,
    val canContinue: Boolean
): State {
    @Inject constructor(): this("", false, "", false, true, false, false)
}