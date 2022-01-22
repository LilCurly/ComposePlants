package com.example.soplant.redux.register

import com.example.soplant.commons.Constants
import com.example.soplant.redux.Reducer
import javax.inject.Inject

class RegisterReducer @Inject constructor(): Reducer<RegisterAction, RegisterViewState> {
    override fun reduce(
        previousState: RegisterViewState,
        currentAction: RegisterAction
    ): RegisterViewState {
        return when (currentAction) {
            is RegisterAction.SignupFailed -> {
                previousState.copy(isSigningUp = false, signUpFailed = true, errorCode = currentAction.errorCode ?: Constants.Error.General.UNEXPECTED_ERROR)
            }
            is RegisterAction.SignupSucceeded -> {
                previousState.copy(isSigningUp = false, signUpSuccessful = true)
            }
            is RegisterAction.SignupProcessStarted -> {
                previousState.copy(isSigningUp = true, signUpFailed = false, signUpSuccessful = false, formValidated = false)
            }
            is RegisterAction.UpdatingEmail -> {
                previousState.copy(email = currentAction.newEmail, canSignUp = previousState.tosChecked && currentAction.newEmail.isNotEmpty() && previousState.username.isNotEmpty() && previousState.password.isNotEmpty() && previousState.selectedCountry != null)
            }
            is RegisterAction.UpdatingUsername -> {
                previousState.copy(username = currentAction.newUsername, canSignUp = previousState.tosChecked && previousState.email.isNotEmpty() && currentAction.newUsername.isNotEmpty() && previousState.password.isNotEmpty() && previousState.selectedCountry != null)
            }
            is RegisterAction.UpdatingPassword -> {
                previousState.copy(password = currentAction.newPassword, canSignUp = previousState.tosChecked && previousState.email.isNotEmpty() && previousState.username.isNotEmpty() && currentAction.newPassword.isNotEmpty() && previousState.selectedCountry != null)
            }
            is RegisterAction.ClickedReadTos -> {
                previousState.copy(tosChecked = currentAction.newStatus, canSignUp = currentAction.newStatus && previousState.email.isNotEmpty() && previousState.username.isNotEmpty() && previousState.password.isNotEmpty() && previousState.selectedCountry != null)
            }
            is RegisterAction.PasswordInvalidated -> {
                previousState.copy(isPasswordValid = false)
            }
            is RegisterAction.EmailInvalidated -> {
                previousState.copy(isEmailValid = false)
            }
            is RegisterAction.SignupClicked -> {
                previousState.copy(isEmailValid = true, isPasswordValid = true, errorCode = "")
            }
            is RegisterAction.FormValidated -> {
                previousState.copy(formValidated = true)
            }
            is RegisterAction.NavigateToConfirmationScreen -> {
                previousState.copy(signUpSuccessful = false)
            }
            is RegisterAction.ProcessSignup -> {
                previousState
            }
            is RegisterAction.SelectingCountry -> {
                previousState.copy(selectedCountry = currentAction.newSelectedCountry, canSignUp = previousState.tosChecked && previousState.email.isNotEmpty() && previousState.username.isNotEmpty() && previousState.password.isNotEmpty() && currentAction.newSelectedCountry != null)
            }
            is RegisterAction.FetchCountries -> {
                previousState
            }
            is RegisterAction.FetchCountriesStarted -> {
                previousState.copy(fetchingCountries = true)
            }
            is RegisterAction.CountriesRetrieved -> {
                previousState.copy(fetchingCountries = false, countries = currentAction.countries)
            }
            is RegisterAction.FailedToRetrieveCountries -> {
                previousState.copy(fetchingCountries = false)
            }
        }
    }
}