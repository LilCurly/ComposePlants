package com.example.soplant.redux.social_signin

import com.example.soplant.redux.Action

sealed class SocialSignInAction: Action {
    data class UpdatingUsername(val username: String): SocialSignInAction()
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
}