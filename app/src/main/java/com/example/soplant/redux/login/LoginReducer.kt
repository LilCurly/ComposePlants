package com.example.soplant.redux.login

import com.example.soplant.redux.Reducer
import javax.inject.Inject

class LoginReducer @Inject constructor(): Reducer<LoginAction, LoginViewState> {
    override fun reduce(previousState: LoginViewState, currentAction: LoginAction): LoginViewState {
        return when(currentAction) {
            is LoginAction.UpdatingPassword -> {
                previousState.copy(password = currentAction.newPassword)
            }
            is LoginAction.UpdatingUsername -> {
                previousState.copy(username = currentAction.newUsername)
            }
            is LoginAction.StartSigningProcess -> {
                previousState.copy(isSigningIn = true, signInFailed = false, signInSuccessful = false)
            }
            is LoginAction.LoginSucceeded -> {
                previousState.copy(isSigningIn = false, signInSuccessful = true)
            }
            is LoginAction.LoginFailed -> {
                previousState.copy(isSigningIn = false, signInFailed = true)
            }
            is LoginAction.LoginClicked -> {
                previousState
            }
        }
    }
}