package com.example.soplant.redux.register

import com.example.soplant.domain.interactors.confirmation.CreateAccount
import com.example.soplant.domain.interactors.confirmation.CreateExploration
import com.example.soplant.domain.interactors.confirmation.CreateWallet
import com.example.soplant.domain.interactors.register.*
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
import javax.inject.Inject

class RegisterDataMiddleware @Inject constructor(
    private val signupUser: SignupUser,
    private val getCountries: GetCountries
    ): Middleware<RegisterAction, RegisterViewState> {
    @ExperimentalCoroutinesApi
    override suspend fun process(
        currentState: RegisterViewState,
        action: RegisterAction,
        coroutineScope: CoroutineScope
    ): Flow<RegisterAction> = channelFlow {
        when (action) {
            is RegisterAction.ProcessSignup -> {
                signupUser(currentState.email, currentState.username, currentState.password, currentState.selectedCountry?.code ?: "").onEach {
                    when (it.status) {
                        Resource.Status.SUCCESS -> {
                            send(RegisterAction.SignupSucceeded)
                            close()
                        }
                        Resource.Status.LOADING -> {
                            send(RegisterAction.SignupProcessStarted)
                        }
                        Resource.Status.ERROR -> {
                            send(RegisterAction.SignupFailed(it.message))
                            close()
                        }
                    }
                }.launchIn(coroutineScope)
            }
            is RegisterAction.FetchCountries -> {
                getCountries().onEach {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                            send(RegisterAction.FetchCountriesStarted)
                        }
                        Resource.Status.SUCCESS -> {
                            send(RegisterAction.CountriesRetrieved(it.data?.map { country ->
                                CustomDropDownModel(name = country.name, imageUrl = country.image, code = country.code)
                            } ?: listOf()))
                            close()
                        }
                        Resource.Status.ERROR -> {
                            send(RegisterAction.FailedToRetrieveCountries)
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