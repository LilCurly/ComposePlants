package com.example.soplant.redux.social_signin

import com.example.soplant.commons.Constants
import com.example.soplant.redux.Reducer
import javax.inject.Inject

class SocialSignInReducer @Inject constructor(): Reducer<SocialSignInAction, SocialSignInViewState> {
    override fun reduce(
        previousState: SocialSignInViewState,
        currentAction: SocialSignInAction
    ): SocialSignInViewState {
        return when (currentAction) {
            is SocialSignInAction.UpdatingUsername -> {
                previousState.copy(username = currentAction.username, canContinue = currentAction.username.isNotEmpty() && previousState.tosChecked && previousState.selectedCountry != null)
            }
            is SocialSignInAction.ClickingTos -> {
                previousState.copy(tosChecked = !previousState.tosChecked, canContinue = !previousState.tosChecked && previousState.username.isNotEmpty() && previousState.selectedCountry != null)
            }
            is SocialSignInAction.ClickingContinue -> {
                previousState.copy(errorCode = "")
            }
            is SocialSignInAction.StartedAttributeUpdate -> {
                previousState.copy(isLoading = true)
            }
            is SocialSignInAction.AttributeUpdateFailed -> {
                previousState.copy(isLoading = false, errorCode = currentAction.errorCode ?: Constants.General.UNEXPECTED_ERROR)
            }
            is SocialSignInAction.AttributeUpdatedSucceeded -> {
                previousState.copy(isLoading = false, updateSuccessful = true)
            }
            is SocialSignInAction.NavigateToWall -> {
                previousState.copy(updateSuccessful = false)
            }
            is SocialSignInAction.ClickingChange -> {
                previousState.copy(errorCode = "")
            }
            is SocialSignInAction.SigningOutStarted -> {
                previousState.copy(isLoading = true)
            }
            is SocialSignInAction.SigningOutSuccessful -> {
                previousState.copy(signOutSuccessful = true, isLoading = false)
            }
            is SocialSignInAction.NavigateToLogin -> {
                previousState.copy(signOutSuccessful = false)
            }
            is SocialSignInAction.SigningOutFailed -> {
                previousState.copy(errorCode = currentAction.errorCode ?: Constants.General.UNEXPECTED_ERROR, isLoading = false)
            }
            is SocialSignInAction.FetchCountries -> {
                previousState
            }
            is SocialSignInAction.FetchCountriesStarted -> {
                previousState.copy(fetchingCountries = true)
            }
            is SocialSignInAction.CountriesRetrieved -> {
                previousState.copy(fetchingCountries = false, countries = currentAction.countries)
            }
            is SocialSignInAction.FailedToRetrieveCountries -> {
                previousState.copy(fetchingCountries = false)
            }
            is SocialSignInAction.SelectingCountry -> {
                previousState.copy(selectedCountry = currentAction.newSelectedCountry, canContinue = previousState.tosChecked && previousState.username.isNotEmpty() && currentAction.newSelectedCountry != null)
            }
        }
    }

}