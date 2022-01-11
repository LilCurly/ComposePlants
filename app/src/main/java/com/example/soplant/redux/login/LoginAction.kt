package com.example.soplant.redux.login

import com.example.soplant.redux.Action

sealed class LoginAction: Action {
    object LoginFailed: LoginAction()
    object LoginSucceeded: LoginAction()
    object StartSigningProcess: LoginAction()
    object LoginClicked: LoginAction()
    data class UpdatingUsername(val newUsername: String): LoginAction()
    data class UpdatingPassword(val newPassword: String): LoginAction()
}