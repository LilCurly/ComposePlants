package com.example.soplant.redux.register

import com.example.soplant.domain.interactors.register.SignupUser
import com.example.soplant.domain.utils.Resource
import com.example.soplant.redux.Middleware
import com.example.soplant.redux.login.LoginAction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class RegisterDataMiddleware @Inject constructor(val signupUser: SignupUser): Middleware<RegisterAction, RegisterViewState> {
    @ExperimentalCoroutinesApi
    override suspend fun process(
        currentState: RegisterViewState,
        action: RegisterAction,
        coroutineScope: CoroutineScope
    ): Flow<RegisterAction> = channelFlow {
        when (action) {
            is RegisterAction.ProcessSignup -> {
                signupUser(currentState.email, currentState.username, currentState.password).onEach {
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
        }
        awaitClose()
    }
}