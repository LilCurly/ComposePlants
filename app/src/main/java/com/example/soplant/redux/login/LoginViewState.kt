package com.example.soplant.redux.login

import com.example.soplant.redux.State
import javax.inject.Inject

data class LoginViewState(
    val isSigningIn: Boolean,
    val signInSuccessful: Boolean,
    val signInFailed: Boolean,
    val username: String,
    val password: String
): State {
    @Inject
    constructor(): this(false, false, false, "", "")
}