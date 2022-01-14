package com.example.soplant.redux.register

import com.example.soplant.redux.Action

sealed class RegisterAction: Action {
    data class SignupFailed(val errorCode: String?): RegisterAction()
    object SignupSucceeded: RegisterAction()
    object SignupProcessStarted: RegisterAction()
    object SignupClicked: RegisterAction()
    object FormValidated: RegisterAction()
    object ProcessSignup: RegisterAction()
    object PasswordInvalidated: RegisterAction()
    object EmailInvalidated: RegisterAction()
    object NavigateToConfirmationScreen: RegisterAction()
    data class UpdatingEmail(val newEmail: String): RegisterAction()
    data class UpdatingUsername(val newUsername: String): RegisterAction()
    data class UpdatingPassword(val newPassword: String): RegisterAction()
    data class ClickedReadTos(val newStatus: Boolean): RegisterAction()
}