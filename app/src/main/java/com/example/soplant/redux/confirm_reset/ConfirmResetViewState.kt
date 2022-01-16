package com.example.soplant.redux.confirm_reset

import com.example.soplant.redux.State
import javax.inject.Inject

data class ConfirmResetViewState(
    val isLoading: Boolean,
    val errorCode: String,
    val newPassword: String,
    val valueOne: String,
    val valueTwo: String,
    val valueThree: String,
    val valueFour: String,
    val valueFive: String,
    val valueSix: String,
    val isPasswordValid: Boolean,
    val canProcessConfirm: Boolean,
    val confirmSucceeded: Boolean,
    val canConfirm: Boolean
): State {
    @Inject constructor(): this(false, "", "", "", "", "", "", "", "", true, false, false, false)
}