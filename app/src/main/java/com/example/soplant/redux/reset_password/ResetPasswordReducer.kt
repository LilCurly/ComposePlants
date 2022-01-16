package com.example.soplant.redux.reset_password

import com.example.soplant.commons.Constants
import com.example.soplant.redux.Reducer
import javax.inject.Inject

class ResetPasswordReducer @Inject constructor(): Reducer<ResetPasswordAction, ResetPasswordViewState> {
    override fun reduce(
        previousState: ResetPasswordViewState,
        currentAction: ResetPasswordAction
    ): ResetPasswordViewState {
        return when (currentAction) {
            is ResetPasswordAction.ClickedNextButton -> {
                previousState.copy(isValidEmail = true, errorCode = "")
            }
            is ResetPasswordAction.UpdatingEmail -> {
                previousState.copy(email = currentAction.email, canContinue = currentAction.email.isNotEmpty())
            }
            is ResetPasswordAction.EmailInvalidated -> {
                previousState.copy(isValidEmail = false)
            }
            is ResetPasswordAction.FormValidated -> {
                previousState.copy(mustVerifyUser = true)
            }
            is ResetPasswordAction.ProcessReset -> {
                previousState.copy(mustVerifyUser = false)
            }
            is ResetPasswordAction.VerifyingUser -> {
                previousState.copy(isVerifyingUser = true)
            }
            is ResetPasswordAction.VerificationFailed -> {
                previousState.copy(errorCode = currentAction.error ?: Constants.General.UNEXPECTED_ERROR)
            }
            is ResetPasswordAction.VerificationSucceeded -> {
                previousState.copy(canProcessNextStep = true)
            }
            is ResetPasswordAction.NavigateToConfirmReset -> {
                previousState.copy(canProcessNextStep = false)
            }
        }
    }
}