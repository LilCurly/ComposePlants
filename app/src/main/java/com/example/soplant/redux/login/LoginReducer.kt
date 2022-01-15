package com.example.soplant.redux.login

import com.example.soplant.commons.Constants
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
                if (currentAction.errorCode.equals(Constants.Amplify.AMPLIFY_USER_NOT_CONFIRMED)) {
                    previousState.copy(isSigningIn = false, signInFailed = false, needsValidation = true)
                } else {
                    previousState.copy(isSigningIn = false, signInFailed = true, errorCode = currentAction.errorCode ?: Constants.Amplify.AMPLIFY_UNEXPECTED_ERROR)
                }
            }
            is LoginAction.NavigateToUserValidation -> {
                previousState.copy(needsValidation = false)
            }
            is LoginAction.NavigateToUserWall -> {
                previousState.copy(signInSuccessful = false)
            }
            is LoginAction.LoginClicked -> {
                previousState
            }
        }
    }
}