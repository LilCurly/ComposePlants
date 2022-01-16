package com.example.soplant.redux.reset_password

import com.example.soplant.redux.Action

sealed class ResetPasswordAction: Action {
    data class UpdatingEmail(val email: String): ResetPasswordAction()
    object ClickedNextButton: ResetPasswordAction()
    object EmailInvalidated: ResetPasswordAction()
    object FormValidated: ResetPasswordAction()
    object ProcessReset: ResetPasswordAction()
    object VerifyingUser: ResetPasswordAction()
    data class VerificationFailed(val error: String?): ResetPasswordAction()
    object VerificationSucceeded: ResetPasswordAction()
    object NavigateToConfirmReset: ResetPasswordAction()
}