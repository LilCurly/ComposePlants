package com.example.soplant.redux.confirm_reset

import com.example.soplant.redux.Reducer
import javax.inject.Inject

class ConfirmResetReducer @Inject constructor(): Reducer<ConfirmResetAction, ConfirmResetViewState> {
    override fun reduce(
        previousState: ConfirmResetViewState,
        currentAction: ConfirmResetAction
    ): ConfirmResetViewState {
        return when (currentAction) {
            is ConfirmResetAction.UpdateNewPassword -> {
                previousState.copy(newPassword = currentAction.newPassword, canConfirm = currentAction.newPassword.isNotEmpty() && previousState.valueOne.isNotEmpty() && previousState.valueTwo.isNotEmpty() && previousState.valueThree.isNotEmpty() && previousState.valueFour.isNotEmpty() && previousState.valueFive.isNotEmpty() && previousState.valueSix.isNotEmpty())
            }
            is ConfirmResetAction.UpdateValueOne -> {
                previousState.copy(valueOne = currentAction.value, canConfirm = previousState.newPassword.isNotEmpty() && currentAction.value.isNotEmpty() && previousState.valueTwo.isNotEmpty() && previousState.valueThree.isNotEmpty() && previousState.valueFour.isNotEmpty() && previousState.valueFive.isNotEmpty() && previousState.valueSix.isNotEmpty())
            }
            is ConfirmResetAction.UpdateValueTwo -> {
                previousState.copy(valueTwo = currentAction.value, canConfirm = previousState.newPassword.isNotEmpty() && previousState.valueOne.isNotEmpty() && currentAction.value.isNotEmpty() && previousState.valueThree.isNotEmpty() && previousState.valueFour.isNotEmpty() && previousState.valueFive.isNotEmpty() && previousState.valueSix.isNotEmpty())
            }
            is ConfirmResetAction.UpdateValueThree -> {
                previousState.copy(valueThree = currentAction.value, canConfirm = previousState.newPassword.isNotEmpty() && previousState.valueOne.isNotEmpty() && previousState.valueTwo.isNotEmpty() && currentAction.value.isNotEmpty() && previousState.valueFour.isNotEmpty() && previousState.valueFive.isNotEmpty() && previousState.valueSix.isNotEmpty())
            }
            is ConfirmResetAction.UpdateValueFour -> {
                previousState.copy(valueFour = currentAction.value, canConfirm = previousState.newPassword.isNotEmpty() && previousState.valueOne.isNotEmpty() && previousState.valueTwo.isNotEmpty() && previousState.valueThree.isNotEmpty() && currentAction.value.isNotEmpty() && previousState.valueFive.isNotEmpty() && previousState.valueSix.isNotEmpty())
            }
            is ConfirmResetAction.UpdateValueFive -> {
                previousState.copy(valueFive = currentAction.value, canConfirm = previousState.newPassword.isNotEmpty() && previousState.valueOne.isNotEmpty() && previousState.valueTwo.isNotEmpty() && previousState.valueThree.isNotEmpty() && previousState.valueFour.isNotEmpty() && currentAction.value.isNotEmpty() && previousState.valueSix.isNotEmpty())
            }
            is ConfirmResetAction.UpdateValueSix -> {
                previousState.copy(valueSix = currentAction.value, canConfirm = previousState.newPassword.isNotEmpty() && previousState.valueOne.isNotEmpty() && previousState.valueTwo.isNotEmpty() && previousState.valueThree.isNotEmpty() && previousState.valueFour.isNotEmpty() && previousState.valueFive.isNotEmpty() && currentAction.value.isNotEmpty())
            }
            is ConfirmResetAction.ClickedConfirm -> {
                previousState.copy(errorCode = "", isPasswordValid = true)
            }
            is ConfirmResetAction.PasswordInvalidated -> {
                previousState.copy(isPasswordValid = false)
            }
            is ConfirmResetAction.FormValidated -> {
                previousState.copy(canProcessConfirm = true)
            }
            is ConfirmResetAction.ProcessConfirmation -> {
                previousState.copy(canProcessConfirm = false)
            }
            is ConfirmResetAction.ConfirmationStarted -> {
                previousState.copy(isLoading = true)
            }
            is ConfirmResetAction.ConfirmationFailed -> {
                previousState.copy(errorCode = currentAction.errorCode)
            }
            is ConfirmResetAction.ConfirmationSuccess -> {
                previousState.copy(confirmSucceeded = true)
            }
            is ConfirmResetAction.NavigateToLogin -> {
                previousState.copy(confirmSucceeded = false)
            }
        }
    }
}