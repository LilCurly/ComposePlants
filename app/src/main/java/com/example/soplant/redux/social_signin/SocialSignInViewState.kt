package com.example.soplant.redux.social_signin

import com.example.soplant.presentation.ui.custom.CustomDropDownModel
import com.example.soplant.redux.State
import javax.inject.Inject

data class SocialSignInViewState(
    val username: String,
    val tosChecked: Boolean,
    val canContinue: Boolean,
    val isLoading: Boolean,
    val errorCode: String,
    val updateSuccessful: Boolean,
    val signOutSuccessful: Boolean,
    val selectedCountry: CustomDropDownModel?,
    val countries: List<CustomDropDownModel>,
    val fetchingCountries: Boolean
): State {
    @Inject constructor(): this("", false, false, false, "", false, false, null, listOf(), false)
}