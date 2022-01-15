package com.example.soplant.redux.login

import com.example.soplant.redux.Action

sealed class LoginAction: Action {
    data class LoginFailed(val errorCode: String?): LoginAction()
    object LoginSucceeded: LoginAction()
    object StartSigningProcess: LoginAction()
    object LoginClicked: LoginAction()
    object NavigateToUserValidation: LoginAction()
    object NavigateToUserWall: LoginAction()
    data class UpdatingUsername(val newUsername: String): LoginAction()
    data class UpdatingPassword(val newPassword: String): LoginAction()
}