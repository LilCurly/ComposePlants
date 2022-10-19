package com.example.soplant.redux.social_signin

import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobile.client.Callback
import com.amazonaws.mobile.client.results.Tokens
import com.amazonaws.mobileconnectors.cognitoidentityprovider.util.CognitoIdentityProviderClientConfig
import com.example.soplant.commons.Constants
import com.example.soplant.commons.UserAttributes
import com.example.soplant.domain.interactors.confirmation.CreateAccount
import com.example.soplant.domain.interactors.confirmation.CreateExploration
import com.example.soplant.domain.interactors.register.GetCountries
import com.example.soplant.domain.interactors.social_signin.FederateSignIn
import com.example.soplant.domain.interactors.social_signin.SignOut
import com.example.soplant.domain.utils.Resource
import com.example.soplant.presentation.ui.custom.CustomDropDownModel
import com.example.soplant.redux.Middleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class SocialSignInDataMiddleware @Inject constructor(
    private val federateSignIn: FederateSignIn,
    private val signOut: SignOut,
    private val getCountries: GetCountries,
    private val createAccount: CreateAccount,
    private val createExploration: CreateExploration
) : Middleware<SocialSignInAction, SocialSignInViewState> {
    @ExperimentalCoroutinesApi
    override suspend fun process(
        currentState: SocialSignInViewState,
        action: SocialSignInAction,
        coroutineScope: CoroutineScope
    ): Flow<SocialSignInAction> = channelFlow {
        when (action) {
            is SocialSignInAction.ClickingContinue -> {
                federateSignIn(
                    currentState.legalName,
                    currentState.firstName,
                    currentState.lastName,
                    currentState.selectedRegisterAs,
                    currentState.selectedLegalEntity,
                    currentState.selectedCountry?.code ?: "",
                    currentState.userImageUrl
                ).onEach {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                            send(SocialSignInAction.StartedAttributeUpdate)
                        }
                        Resource.Status.ERROR -> {
                            send(SocialSignInAction.AttributeUpdateFailed(it.message))
                            close()
                        }
                        Resource.Status.SUCCESS -> {
                            createAccount().onEach { resource ->
                                when (resource.status) {
                                    Resource.Status.ERROR -> {
                                        //TODO: Something ?
                                        send(SocialSignInAction.AttributeUpdateFailed(it.message))
                                        close()
                                    }
                                    Resource.Status.SUCCESS -> {
                                        Thread {
                                            try {
                                                CognitoIdentityProviderClientConfig.setRefreshThreshold(
                                                    1800000
                                                )
                                                AWSMobileClient.getInstance().getTokens(object :
                                                    Callback<Tokens> {
                                                    override fun onResult(result: Tokens?) {
                                                        coroutineScope.launch {
                                                            UserAttributes.fetchUserAttributes()
                                                                .collect { success ->
                                                                    if (success) {
                                                                        send(SocialSignInAction.AttributeUpdatedSucceeded)
                                                                        close()
                                                                    } else {
                                                                        //TODO: Something ?
                                                                        send(
                                                                            SocialSignInAction.AttributeUpdateFailed(
                                                                                it.message
                                                                            )
                                                                        )
                                                                        close()
                                                                    }
                                                                }
                                                        }
                                                    }

                                                    override fun onError(e: java.lang.Exception?) {
                                                        //TODO: Something ?
                                                        coroutineScope.launch {
                                                            send(
                                                                SocialSignInAction.AttributeUpdateFailed(
                                                                    it.message
                                                                )
                                                            )
                                                            close()
                                                        }
                                                    }

                                                })
                                            } catch (e: Exception) {
                                                e.printStackTrace()
                                            }
                                        }.start()
                                    }
                                    Resource.Status.LOADING -> {}
                                }
                            }.launchIn(coroutineScope)
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
                            send(
                                SocialSignInAction.SigningOutFailed(
                                    it.message ?: Constants.Error.General.UNEXPECTED_ERROR
                                )
                            )
                            close()
                        }
                    }
                }.launchIn(coroutineScope)
            }
            is SocialSignInAction.FetchCountries -> {
                getCountries(true).onEach {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                            send(SocialSignInAction.FetchCountriesStarted)
                        }
                        Resource.Status.SUCCESS -> {
                            send(SocialSignInAction.CountriesRetrieved(it.data?.map { country ->
                                CustomDropDownModel(
                                    name = country.name.common,
                                    imageUrl = country.image.png,
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
            else -> {}
        }
        awaitClose()
    }
}