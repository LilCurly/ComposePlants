package com.example.soplant.redux.login

import com.example.soplant.commons.UserAttributes
import com.example.soplant.domain.interactors.confirmation.ResendCode
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
import javax.inject.Inject

class LoginDataMiddleware @Inject constructor(private val loginWithCredentials: LoginWithCredentials,
                                              private val resendCode: ResendCode): Middleware<LoginAction, LoginViewState> {
    @ExperimentalCoroutinesApi
    override suspend fun process(
        currentState: LoginViewState,
        action: LoginAction,
        coroutineScope: CoroutineScope
    ): Flow<LoginAction> = channelFlow {
        when (action) {
            is LoginAction.LoginClicked -> {
                loginWithCredentials(currentState.username, currentState.password).onEach {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                            send(LoginAction.StartSigningProcess)
                        }
                        Resource.Status.ERROR -> {
                            send(LoginAction.LoginFailed(it.message))
                            close()
                        }
                        Resource.Status.SUCCESS -> {
                            if (it.data == true) {
                                UserAttributes.fetchUserAttributes().onEach { success ->
                                    if (success) {
                                        send(LoginAction.LoginSucceeded)
                                    }
                                    close()
                                }.launchIn(coroutineScope)
                            } else {
                                send(LoginAction.LoginFailed(it.message))
                                close()
                            }
                        }
                    }
                }.launchIn(coroutineScope)
            }
            is LoginAction.NavigateToUserValidation -> {
                resendCode(currentState.username).launchIn(coroutineScope)
            }
        }
        awaitClose()
    }
}