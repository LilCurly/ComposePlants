package com.example.soplant.redux.register

import com.example.soplant.presentation.ui.custom.CustomDropDownModel
import com.example.soplant.redux.Action

sealed class RegisterAction: Action {
    data class SignupFailed(val errorCode: String?): RegisterAction()
    object SignupSucceeded: RegisterAction()
    object SignupProcessStarted: RegisterAction()
    object SignupClicked: RegisterAction()
    object FormValidated: RegisterAction()
    object ProcessSignup: RegisterAction()
    object PasswordInvalidated: RegisterAction()
    object EmailInvalidated: RegisterAction()
    object NavigateToConfirmationScreen: RegisterAction()
    object FetchCountries: RegisterAction()
    object FetchCountriesStarted: RegisterAction()
    data class CountriesRetrieved(val countries: List<CustomDropDownModel>): RegisterAction()
    object FailedToRetrieveCountries: RegisterAction()
    data class UpdatingEmail(val newEmail: String): RegisterAction()
    data class UpdatingLegalName(val newLegalName: String): RegisterAction()
    data class UpdatingFirstName(val newFirstName: String): RegisterAction()
    data class UpdatingLastName(val newLastName: String): RegisterAction()
    data class UpdatingPassword(val newPassword: String): RegisterAction()
    data class ClickedReadTos(val newStatus: Boolean): RegisterAction()
    data class SelectingCountry(val newSelectedCountry: CustomDropDownModel?): RegisterAction()
    data class SelectingRegisterAs(val newRegisterAsValue: String): RegisterAction()
    data class SelectingLegalEntity(val newLegalEntity: String): RegisterAction()
    object NavigatingBack: RegisterAction()
}