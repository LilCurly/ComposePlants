package com.example.soplant.redux.social_signin

import com.example.soplant.presentation.ui.custom.CustomDropDownModel
import com.example.soplant.redux.State
import javax.inject.Inject

data class SocialSignInViewState(
    val legalName: String,
    val firstName: String,
    val lastName: String,
    val tosChecked: Boolean,
    val canContinue: Boolean,
    val isLoading: Boolean,
    val errorCode: String,
    val updateSuccessful: Boolean,
    val signOutSuccessful: Boolean,
    val selectedCountry: CustomDropDownModel?,
    val countries: List<CustomDropDownModel>,
    val fetchingCountries: Boolean,
    val userImageUrl: String,
    val selectedRegisterAs: String,
    val selectedLegalEntity: String,
    val screenState: SocialSignInScreenState
): State {
    @Inject constructor(): this("", "", "", false, false, false, "", false, false, null, listOf(), false, "", "", "", SocialSignInScreenState.REGISTER_AS_CHOICE)

    enum class RegisterAsOptions(val optionValue: String) {
        USER_TYPE_NATURAL("USER_TYPE_NATURAL"),
        USER_TYPE_LEGAL("USER_TYPE_LEGAL")
    }

    enum class LegalEntityOptions(val optionValue: String) {
        SOLETRADER("SOLETRADER"),
        BUSINESS("BUSINESS"),
        ORGANIZATION("ORGANIZATION"),
    }

    enum class SocialSignInScreenState {
        REGISTER_AS_CHOICE,
        LEGAL_ENTITY_CHOICE,
        FORM
    }
}