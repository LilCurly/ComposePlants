package com.example.soplant.redux.confirm_reset

import com.example.soplant.redux.Action

sealed class ConfirmResetAction: Action {
    data class UpdateValueOne(val value: String): ConfirmResetAction()
    data class UpdateValueTwo(val value: String): ConfirmResetAction()
    data class UpdateValueThree(val value: String): ConfirmResetAction()
    data class UpdateValueFour(val value: String): ConfirmResetAction()
    data class UpdateValueFive(val value: String): ConfirmResetAction()
    data class UpdateValueSix(val value: String): ConfirmResetAction()
    data class UpdateNewPassword(val newPassword: String): ConfirmResetAction()
    object ClickedConfirm: ConfirmResetAction()
    object PasswordInvalidated: ConfirmResetAction()
    object FormValidated: ConfirmResetAction()
    object ProcessConfirmation: ConfirmResetAction()
    object ConfirmationSuccess: ConfirmResetAction()
    data class ConfirmationFailed(val errorCode: String): ConfirmResetAction()
    object NavigateToLogin: ConfirmResetAction()
    object ConfirmationStarted: ConfirmResetAction()
}