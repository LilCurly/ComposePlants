package com.example.soplant.redux.confirmation

import com.example.soplant.redux.State

data class ConfirmationViewState(
    val isValidating: Boolean,
    val validationSuccessful: Boolean
): State {
}