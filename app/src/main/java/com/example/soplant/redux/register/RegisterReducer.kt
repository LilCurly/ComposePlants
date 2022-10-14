package com.example.soplant.redux.register

import com.example.soplant.commons.Constants
import com.example.soplant.redux.Reducer
import javax.inject.Inject

class RegisterReducer @Inject constructor() : Reducer<RegisterAction, RegisterViewState> {
    override fun reduce(
        previousState: RegisterViewState,
        currentAction: RegisterAction
    ): RegisterViewState {
        return when (currentAction) {
            is RegisterAction.SignupFailed -> {
                previousState.copy(
                    isSigningUp = false,
                    signUpFailed = true,
                    errorCode = currentAction.errorCode ?: Constants.Error.General.UNEXPECTED_ERROR
                )
            }
            is RegisterAction.SignupSucceeded -> {
                previousState.copy(isSigningUp = false, signUpSuccessful = true)
            }
            is RegisterAction.SignupProcessStarted -> {
                previousState.copy(
                    isSigningUp = true,
                    signUpFailed = false,
                    signUpSuccessful = false,
                    formValidated = false
                )
            }
            is RegisterAction.UpdatingEmail -> {
                signUpState(
                    previousState.copy(
                        email = currentAction.newEmail,
                    )
                )
            }
            is RegisterAction.UpdatingLegalName -> {
                signUpState(
                    previousState.copy(
                        legalName = currentAction.newLegalName,
                    )
                )
            }
            is RegisterAction.UpdatingFirstName -> {
                signUpState(
                    previousState.copy(
                        firstName = currentAction.newFirstName,
                    )
                )
            }
            is RegisterAction.UpdatingLastName -> {
                signUpState(
                    previousState.copy(
                        lastName = currentAction.newLastName,
                    )
                )
            }
            is RegisterAction.UpdatingPassword -> {
                signUpState(
                    previousState.copy(
                        password = currentAction.newPassword
                    )
                )
            }
            is RegisterAction.ClickedReadTos -> {
                signUpState(
                    previousState.copy(
                        tosChecked = currentAction.newStatus
                    )
                )
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
                signUpState(
                    previousState.copy(
                        selectedCountry = currentAction.newSelectedCountry
                    )
                )
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
            is RegisterAction.SelectingRegisterAs -> {
                previousState.copy(
                    selectedRegisterAs = currentAction.newRegisterAsValue,
                    currentScreenState = if (currentAction.newRegisterAsValue == RegisterViewState.RegisterAsOptions.USER_TYPE_LEGAL.optionValue) RegisterScreenState.LEGAL_ENTITY_CHOICE else RegisterScreenState.FORM
                )
            }
            is RegisterAction.SelectingLegalEntity -> {
                previousState.copy(
                    selectedLegalEntity = currentAction.newLegalEntity,
                    currentScreenState = RegisterScreenState.FORM
                )
            }
            is RegisterAction.NavigatingBack -> {
                previousState.copy(currentScreenState = if (previousState.currentScreenState == RegisterScreenState.FORM && previousState.selectedRegisterAs == RegisterViewState.RegisterAsOptions.USER_TYPE_LEGAL.optionValue) RegisterScreenState.LEGAL_ENTITY_CHOICE else RegisterScreenState.REGISTER_AS_CHOICE)
            }
        }
    }

    private fun signUpState(previousState: RegisterViewState): RegisterViewState {
        return if (previousState.selectedRegisterAs == RegisterViewState.RegisterAsOptions.USER_TYPE_LEGAL.optionValue) {
            previousState.copy(canSignUp = previousState.tosChecked && previousState.email.isNotEmpty() && previousState.firstName.isNotEmpty() && previousState.lastName.isNotEmpty() && previousState.password.isNotEmpty() && previousState.selectedCountry != null && previousState.legalName.isNotEmpty())
        } else {
            previousState.copy(canSignUp = previousState.tosChecked && previousState.email.isNotEmpty() && previousState.firstName.isNotEmpty() && previousState.lastName.isNotEmpty() && previousState.password.isNotEmpty() && previousState.selectedCountry != null)
        }

    }
}