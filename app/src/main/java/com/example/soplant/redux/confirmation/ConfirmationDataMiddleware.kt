package com.example.soplant.redux.confirmation

import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobile.client.Callback
import com.amazonaws.mobile.client.results.Tokens
import com.amazonaws.mobileconnectors.cognitoidentityprovider.util.CognitoIdentityProviderClientConfig
import com.example.soplant.commons.UserAttributes
import com.example.soplant.domain.interactors.confirmation.CreateAccount
import com.example.soplant.domain.interactors.confirmation.CreateExploration
import com.example.soplant.domain.interactors.confirmation.ResendCode
import com.example.soplant.domain.interactors.confirmation.ValidateUser
import com.example.soplant.domain.interactors.login.LoginWithCredentials
import com.example.soplant.domain.utils.Resource
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

class ConfirmationDataMiddleware @Inject constructor(
    private val validateUser: ValidateUser,
    private val resendCode: ResendCode,
    private val loginWithCredentials: LoginWithCredentials,
    private val createAccount: CreateAccount,
    private val createExploration: CreateExploration
) : Middleware<ConfirmationAction, ConfirmationViewState> {
    @ExperimentalCoroutinesApi
    override suspend fun process(
        currentState: ConfirmationViewState,
        action: ConfirmationAction,
        coroutineScope: CoroutineScope
    ): Flow<ConfirmationAction> = channelFlow {
        when (action) {
            is ConfirmationAction.ConfirmClicked -> {
                validateUser(
                    currentState.email,
                    "${currentState.valueOne}${currentState.valueTwo}${currentState.valueThree}${currentState.valueFour}${currentState.valueFive}${currentState.valueSix}"
                ).onEach {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                            send(ConfirmationAction.ConfirmationProcessStarted)
                        }
                        Resource.Status.SUCCESS -> {
                            send(ConfirmationAction.ConfirmationSucceeded)
                            close()
                        }
                        Resource.Status.ERROR -> {
                            send(ConfirmationAction.ConfirmationFailed(it.message))
                            close()
                        }
                    }
                }.launchIn(coroutineScope)
            }
            is ConfirmationAction.ResendClicked -> {
                resendCode(currentState.email).onEach {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                            send(ConfirmationAction.ResendProcessStarted)
                        }
                        Resource.Status.SUCCESS -> {
                            send(ConfirmationAction.ResendSucceeded)
                            close()
                        }
                        Resource.Status.ERROR -> {
                            send(ConfirmationAction.ResendFailed(it.message))
                            close()
                        }
                    }
                }.launchIn(coroutineScope)
            }
            is ConfirmationAction.AuthUser -> {
                loginWithCredentials(currentState.email, currentState.password).onEach {
                    when (it.status) {
                        Resource.Status.SUCCESS -> {
                            createAccount().onEach { resource ->
                                when (resource.status) {
                                    Resource.Status.ERROR -> {
                                        //TODO: Something ?
                                        send(ConfirmationAction.AuthFailed)
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
                                                                        send(ConfirmationAction.AuthSucceeded)
                                                                        close()
                                                                    } else {
                                                                        //TODO: Something ?
                                                                        send(
                                                                            ConfirmationAction.AuthFailed
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
                                                                ConfirmationAction.AuthFailed
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
                        Resource.Status.ERROR -> {
                            send(ConfirmationAction.AuthFailed)
                            close()
                        }
                        else -> {}
                    }
                }.launchIn(coroutineScope)
            }
            else -> {}
        }
        awaitClose()
    }
}