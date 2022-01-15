package com.example.soplant.redux.confirmation

import com.example.soplant.redux.State
import javax.inject.Inject

data class ConfirmationViewState(
    val isLoading: Boolean,
    val validationSuccessful: Boolean,
    val authSucceeded: Boolean,
    val authFailed: Boolean,
    val errorCode: String,
    val canConfirm: Boolean,
    val valueOne: String,
    val valueTwo: String,
    val valueThree: String,
    val valueFour: String,
    val valueFive: String,
    val valueSix: String,
    val email: String,
    val password: String
): State {
    @Inject constructor(): this(false, false, false, false, "", false, "", "", "", "", "", "", "", "")
}