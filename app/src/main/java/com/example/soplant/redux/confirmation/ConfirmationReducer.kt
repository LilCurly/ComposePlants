package com.example.soplant.redux.confirmation

import com.example.soplant.commons.Constants
import com.example.soplant.redux.Reducer
import javax.inject.Inject

class ConfirmationReducer @Inject constructor() : Reducer<ConfirmationAction, ConfirmationViewState> {
    override fun reduce(
        previousState: ConfirmationViewState,
        currentAction: ConfirmationAction
    ): ConfirmationViewState {
        return when (currentAction) {
            is ConfirmationAction.ResendProcessStarted, ConfirmationAction.ConfirmationProcessStarted -> {
                previousState.copy(isLoading = true, errorCode = "")
            }
            is ConfirmationAction.ResendFailed -> {
                previousState.copy(isLoading = false, errorCode = currentAction.errorCode ?: Constants.Error.General.UNEXPECTED_ERROR)
            }
            is ConfirmationAction.ResendSucceeded -> {
                previousState.copy(isLoading = false)
            }
            is ConfirmationAction.ConfirmationFailed -> {
                previousState.copy(isLoading = false, errorCode = currentAction.errorCode ?: Constants.Error.General.UNEXPECTED_ERROR)
            }
            is ConfirmationAction.ConfirmationSucceeded -> {
                previousState.copy(validationSuccessful = true)
            }
            is ConfirmationAction.UpdatingValueOne -> {
                val newValue = if (currentAction.newValue.isNotEmpty()) currentAction.newValue.last().toString() else ""
                previousState.copy(valueOne = newValue, canConfirm = currentAction.newValue.isNotEmpty() && previousState.valueTwo.isNotEmpty() && previousState.valueThree.isNotEmpty() && previousState.valueFour.isNotEmpty() && previousState.valueFive.isNotEmpty() && previousState.valueSix.isNotEmpty())
            }
            is ConfirmationAction.UpdatingValueTwo -> {
                val newValue = if (currentAction.newValue.isNotEmpty()) currentAction.newValue.last().toString() else ""
                previousState.copy(valueTwo = newValue, canConfirm = previousState.valueOne.isNotEmpty() && newValue.isNotEmpty() && previousState.valueThree.isNotEmpty() && previousState.valueFour.isNotEmpty() && previousState.valueFive.isNotEmpty() && previousState.valueSix.isNotEmpty())
            }
            is ConfirmationAction.UpdatingValueThree -> {
                val newValue = if (currentAction.newValue.isNotEmpty()) currentAction.newValue.last().toString() else ""
                previousState.copy(valueThree = newValue, canConfirm = previousState.valueOne.isNotEmpty() && previousState.valueTwo.isNotEmpty() && newValue.isNotEmpty() && previousState.valueFour.isNotEmpty() && previousState.valueFive.isNotEmpty() && previousState.valueSix.isNotEmpty())
            }
            is ConfirmationAction.UpdatingValueFour -> {
                val newValue = if (currentAction.newValue.isNotEmpty()) currentAction.newValue.last().toString() else ""
                previousState.copy(valueFour = newValue, canConfirm = previousState.valueOne.isNotEmpty() && previousState.valueTwo.isNotEmpty() && previousState.valueThree.isNotEmpty() && newValue.isNotEmpty() && previousState.valueFive.isNotEmpty() && previousState.valueSix.isNotEmpty())
            }
            is ConfirmationAction.UpdatingValueFive -> {
                val newValue = if (currentAction.newValue.isNotEmpty()) currentAction.newValue.last().toString() else ""
                previousState.copy(valueFive = newValue, canConfirm = previousState.valueOne.isNotEmpty() && previousState.valueTwo.isNotEmpty() && previousState.valueThree.isNotEmpty() && previousState.valueFour.isNotEmpty() && newValue.isNotEmpty() && previousState.valueSix.isNotEmpty())
            }
            is ConfirmationAction.UpdatingValueSix -> {
                val newValue = if (currentAction.newValue.isNotEmpty()) currentAction.newValue.last().toString() else ""
                previousState.copy(valueSix = newValue, canConfirm = previousState.valueOne.isNotEmpty() && previousState.valueTwo.isNotEmpty() && previousState.valueThree.isNotEmpty() && previousState.valueFour.isNotEmpty() && previousState.valueFive.isNotEmpty() && newValue.isNotEmpty())
            }
            is ConfirmationAction.SetUserEmail -> {
                previousState.copy(email = if (currentAction.userEmail.isNotEmpty()) currentAction.userEmail else "0")
            }
            is ConfirmationAction.SetUserPassword -> {
                previousState.copy(password = if (currentAction.userPassword.isNotEmpty()) currentAction.userPassword else "0")
            }
            is ConfirmationAction.NavigateToUserWall -> {
                previousState.copy(authSucceeded = false)
            }
            is ConfirmationAction.NavigateToLogin -> {
                previousState.copy(authFailed = false)
            }
            is ConfirmationAction.AuthUser -> {
                previousState.copy(validationSuccessful = false)
            }
            is ConfirmationAction.AuthSucceeded -> {
                previousState.copy(authSucceeded = true)
            }
            is ConfirmationAction.AuthFailed -> {
                previousState.copy(authFailed = true)
            }
            is ConfirmationAction.ConfirmClicked, ConfirmationAction.ResendClicked -> {
                previousState
            }
        }
    }
}