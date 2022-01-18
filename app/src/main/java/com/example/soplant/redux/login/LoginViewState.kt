package com.example.soplant.redux.login

import com.example.soplant.redux.State
import javax.inject.Inject

data class LoginViewState(
    val isSigningIn: Boolean,
    val signInSuccessful: Boolean,
    val socialSignInSuccessful: Boolean,
    val signInFailed: Boolean,
    val username: String,
    val password: String,
    val errorCode: String,
    val needsValidation: Boolean,
    val shouldNavigateToSocialSignIn: Boolean
): State {
    @Inject
    constructor(): this(false, false, false, false, "", "", "", false, false)
}