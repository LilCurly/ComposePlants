package com.example.soplant.redux.social_signin

import com.example.soplant.presentation.ui.custom.CustomDropDownModel
import com.example.soplant.redux.Action
import com.example.soplant.redux.register.RegisterAction

sealed class SocialSignInAction: Action {
    data class UpdatingFirstName(val firstName: String): SocialSignInAction()
    data class UpdatingLastName(val lastName: String): SocialSignInAction()
    data class SelectingRegisterAs(val registerAs: String): SocialSignInAction()
    data class SelectingLegalEntity(val legalEntity: String): SocialSignInAction()
    data class UpdatingUserImageUrl(val userImageUrl: String): SocialSignInAction()
    object ClickingTos: SocialSignInAction()
    object ClickingContinue: SocialSignInAction()
    object ClickingChange: SocialSignInAction()
    object SigningOutStarted: SocialSignInAction()
    object SigningOutSuccessful: SocialSignInAction()
    data class SigningOutFailed(val errorCode: String?): SocialSignInAction()
    object StartedAttributeUpdate: SocialSignInAction()
    data class AttributeUpdateFailed(val errorCode: String?): SocialSignInAction()
    object AttributeUpdatedSucceeded: SocialSignInAction()
    object NavigateToWall: SocialSignInAction()
    object NavigateToLogin: SocialSignInAction()
    object FetchCountries: SocialSignInAction()
    object FetchCountriesStarted: SocialSignInAction()
    data class CountriesRetrieved(val countries: List<CustomDropDownModel>): SocialSignInAction()
    object FailedToRetrieveCountries: SocialSignInAction()
    data class SelectingCountry(val newSelectedCountry: CustomDropDownModel?): SocialSignInAction()
    data class UpdatingLegalName(val newLegalName: String): SocialSignInAction()
    object NavigateBack: SocialSignInAction()
}