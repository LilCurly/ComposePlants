package com.example.soplant.redux.social_signin

import com.example.soplant.commons.Constants
import com.example.soplant.commons.UserAttributes
import com.example.soplant.domain.interactors.social_signin.FederateSignIn
import com.example.soplant.domain.interactors.social_signin.SignOut
import com.example.soplant.domain.utils.Resource
import com.example.soplant.redux.Middleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SocialSignInDataMiddleware @Inject constructor(private val federateSignIn: FederateSignIn, private val signOut: SignOut): Middleware<SocialSignInAction, SocialSignInViewState> {
    @ExperimentalCoroutinesApi
    override suspend fun process(
        currentState: SocialSignInViewState,
        action: SocialSignInAction,
        coroutineScope: CoroutineScope
    ): Flow<SocialSignInAction> = channelFlow {
        when (action) {
            is SocialSignInAction.ClickingContinue -> {
                federateSignIn(currentState.username, "BE").onEach {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                            send(SocialSignInAction.StartedAttributeUpdate)
                        }
                        Resource.Status.ERROR -> {
                            send(SocialSignInAction.AttributeUpdateFailed(it.message))
                            close()
                        }
                        Resource.Status.SUCCESS -> {
                            UserAttributes.fetchUserAttributes().collect { success ->
                                if (success) {
                                    send(SocialSignInAction.AttributeUpdatedSucceeded)
                                } else {
                                    send(SocialSignInAction.AttributeUpdateFailed(it.message))
                                }
                            }
                            close()
                        }
                    }
                }.launchIn(coroutineScope)
            }
            is SocialSignInAction.ClickingChange -> {
                signOut().onEach {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                            send(SocialSignInAction.SigningOutStarted)
                        }
                        Resource.Status.SUCCESS -> {
                            send(SocialSignInAction.SigningOutSuccessful)
                            close()
                        }
                        Resource.Status.ERROR -> {
                            send(SocialSignInAction.SigningOutFailed(it.message ?: Constants.General.UNEXPECTED_ERROR))
                            close()
                        }
                    }
                }.launchIn(coroutineScope)
            }
        }
        awaitClose()
    }
}