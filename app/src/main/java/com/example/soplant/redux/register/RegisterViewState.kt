package com.example.soplant.redux.register

import com.example.soplant.presentation.ui.custom.CustomDropDownModel
import com.example.soplant.redux.State
import javax.inject.Inject

data class RegisterViewState(
    val isSigningUp: Boolean,
    val signUpSuccessful: Boolean,
    val signUpFailed: Boolean,
    val legalName: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val tosChecked: Boolean,
    val canSignUp: Boolean,
    val isEmailValid: Boolean,
    val isPasswordValid: Boolean,
    val formValidated: Boolean,
    val errorCode: String,
    val selectedCountry: CustomDropDownModel?,
    val countries: List<CustomDropDownModel>,
    val fetchingCountries: Boolean,
    val selectedRegisterAs: String,
    var selectedLegalEntity: String,
    var currentScreenState: RegisterScreenState
): State {
    @Inject
    constructor(): this(false, false, false, "", "", "", "", "", false, false, true, true, false, "", null, listOf(), false, "", "", RegisterScreenState.REGISTER_AS_CHOICE)

    enum class RegisterAsOptions(val optionValue: String) {
        USER_TYPE_NATURAL("USER_TYPE_NATURAL"),
        USER_TYPE_LEGAL("USER_TYPE_LEGAL")
    }

    enum class LegalEntityOptions(val optionValue: String) {
        SOLETRADER("SOLETRADER"),
        BUSINESS("BUSINESS"),
        ORGANIZATION("ORGANIZATION"),
    }
}

enum class RegisterScreenState {
    REGISTER_AS_CHOICE,
    LEGAL_ENTITY_CHOICE,
    FORM
}