package com.example.soplant.redux.social_signin

import com.example.soplant.commons.Constants
import com.example.soplant.redux.Reducer
import javax.inject.Inject

class SocialSignInReducer @Inject constructor() :
    Reducer<SocialSignInAction, SocialSignInViewState> {
    override fun reduce(
        previousState: SocialSignInViewState,
        currentAction: SocialSignInAction
    ): SocialSignInViewState {
        return when (currentAction) {
            is SocialSignInAction.UpdatingFirstName -> {
                canContinueState(previousState.copy(firstName = currentAction.firstName))
            }
            is SocialSignInAction.UpdatingLastName -> {
                canContinueState(previousState.copy(lastName = currentAction.lastName))
            }
            is SocialSignInAction.UpdatingLegalName -> {
                canContinueState(previousState.copy(legalName = currentAction.newLegalName))
            }
            is SocialSignInAction.ClickingTos -> {
                canContinueState(previousState.copy(tosChecked = !previousState.tosChecked))
            }
            is SocialSignInAction.SelectingRegisterAs -> {
                previousState.copy(
                    selectedRegisterAs = currentAction.registerAs,
                    screenState = if (currentAction.registerAs == SocialSignInViewState.RegisterAsOptions.USER_TYPE_NATURAL.optionValue) SocialSignInViewState.SocialSignInScreenState.FORM else SocialSignInViewState.SocialSignInScreenState.LEGAL_ENTITY_CHOICE
                )
            }
            is SocialSignInAction.SelectingLegalEntity -> {
                previousState.copy(
                    selectedLegalEntity = currentAction.legalEntity,
                    screenState = SocialSignInViewState.SocialSignInScreenState.FORM
                )
            }
            is SocialSignInAction.NavigateBack -> {
                previousState.copy(screenState = if (previousState.screenState == SocialSignInViewState.SocialSignInScreenState.LEGAL_ENTITY_CHOICE) SocialSignInViewState.SocialSignInScreenState.REGISTER_AS_CHOICE else if (previousState.selectedRegisterAs == SocialSignInViewState.RegisterAsOptions.USER_TYPE_LEGAL.optionValue) SocialSignInViewState.SocialSignInScreenState.LEGAL_ENTITY_CHOICE else SocialSignInViewState.SocialSignInScreenState.REGISTER_AS_CHOICE)
            }
            is SocialSignInAction.ClickingContinue -> {
                previousState.copy(errorCode = "")
            }
            is SocialSignInAction.StartedAttributeUpdate -> {
                previousState.copy(isLoading = true)
            }
            is SocialSignInAction.AttributeUpdateFailed -> {
                previousState.copy(
                    isLoading = false,
                    errorCode = currentAction.errorCode ?: Constants.Error.General.UNEXPECTED_ERROR
                )
            }
            is SocialSignInAction.AttributeUpdatedSucceeded -> {
                previousState.copy(updateSuccessful = true)
            }
            is SocialSignInAction.NavigateToWall -> {
                previousState.copy(updateSuccessful = false, isLoading = false)
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
                previousState.copy(
                    errorCode = currentAction.errorCode ?: Constants.Error.General.UNEXPECTED_ERROR,
                    isLoading = false
                )
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
                canContinueState(previousState.copy(selectedCountry = currentAction.newSelectedCountry))
            }
            is SocialSignInAction.UpdatingUserImageUrl -> {
                previousState.copy(userImageUrl = currentAction.userImageUrl)
            }
        }
    }

    private fun canContinueState(previousState: SocialSignInViewState): SocialSignInViewState {
        return if (previousState.selectedRegisterAs == SocialSignInViewState.RegisterAsOptions.USER_TYPE_LEGAL.optionValue) {
            previousState.copy(canContinue = previousState.tosChecked && previousState.legalName.isNotEmpty() && previousState.firstName.isNotEmpty() && previousState.lastName.isNotEmpty() && previousState.selectedCountry != null)
        } else {

            previousState.copy(canContinue = previousState.tosChecked && previousState.firstName.isNotEmpty() && previousState.lastName.isNotEmpty() && previousState.selectedCountry != null)
        }
    }
}