package com.example.soplant.redux.reset_password

import com.example.soplant.domain.interactors.reset_password.ResetPassword
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

class ResetPasswordDataMiddleware @Inject constructor(val resetPassword: ResetPassword): Middleware<ResetPasswordAction, ResetPasswordViewState> {
    @ExperimentalCoroutinesApi
    override suspend fun process(
        currentState: ResetPasswordViewState,
        action: ResetPasswordAction,
        coroutineScope: CoroutineScope
    ): Flow<ResetPasswordAction> = channelFlow {
        when (action) {
            is ResetPasswordAction.ProcessReset -> {
                resetPassword(currentState.email).onEach {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                            send(ResetPasswordAction.VerifyingUser)
                        }
                        Resource.Status.ERROR -> {
                            send(ResetPasswordAction.VerificationFailed(it.message))
                            close()
                        }
                        Resource.Status.SUCCESS -> {
                            send(ResetPasswordAction.VerificationSucceeded)
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