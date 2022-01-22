package com.example.soplant.redux.social_signin

import com.example.soplant.commons.Constants
import com.example.soplant.commons.UserAttributes
import com.example.soplant.domain.interactors.confirmation.CreateAccount
import com.example.soplant.domain.interactors.confirmation.CreateExploration
import com.example.soplant.domain.interactors.confirmation.CreateWallet
import com.example.soplant.domain.interactors.register.GetCountries
import com.example.soplant.domain.interactors.social_signin.FederateSignIn
import com.example.soplant.domain.interactors.social_signin.SignOut
import com.example.soplant.domain.utils.Resource
import com.example.soplant.presentation.ui.custom.CustomDropDownModel
import com.example.soplant.redux.Middleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SocialSignInDataMiddleware @Inject constructor(
    private val federateSignIn: FederateSignIn,
    private val signOut: SignOut,
    private val getCountries: GetCountries,
    private val createAccount: CreateAccount,
    private val createWallet: CreateWallet,
    private val createExploration: CreateExploration
): Middleware<SocialSignInAction, SocialSignInViewState> {
    @ExperimentalCoroutinesApi
    override suspend fun process(
        currentState: SocialSignInViewState,
        action: SocialSignInAction,
        coroutineScope: CoroutineScope
    ): Flow<SocialSignInAction> = channelFlow {
        when (action) {
            is SocialSignInAction.ClickingContinue -> {
                federateSignIn(currentState.username, currentState.selectedCountry?.code ?: "", currentState.userImageUrl).onEach {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                            send(SocialSignInAction.StartedAttributeUpdate)
                        }
                        Resource.Status.ERROR -> {
                            send(SocialSignInAction.AttributeUpdateFailed(it.message))
                            close()
                        }
                        Resource.Status.SUCCESS -> {
                            createAccount().launchIn(coroutineScope)
                            createWallet().launchIn(coroutineScope)
                            createExploration().launchIn(coroutineScope)
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
                            send(SocialSignInAction.SigningOutFailed(it.message ?: Constants.Error.General.UNEXPECTED_ERROR))
                            close()
                        }
                    }
                }.launchIn(coroutineScope)
            }
            is SocialSignInAction.FetchCountries -> {
                getCountries().onEach {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                            send(SocialSignInAction.FetchCountriesStarted)
                        }
                        Resource.Status.SUCCESS -> {
                            send(SocialSignInAction.CountriesRetrieved(it.data?.map { country ->
                                CustomDropDownModel(
                                    name = country.name,
                                    imageUrl = country.image,
                                    code = country.code
                                )
                            } ?: listOf()))
                            close()
                        }
                        Resource.Status.ERROR -> {
                            send(SocialSignInAction.FailedToRetrieveCountries)
                            close()
                        }
                    }
                }.launchIn(coroutineScope)
            }
        }
        awaitClose()
    }
}