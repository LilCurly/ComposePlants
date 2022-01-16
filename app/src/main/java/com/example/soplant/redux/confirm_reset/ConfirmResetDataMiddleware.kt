package com.example.soplant.redux.confirm_reset

import com.example.soplant.commons.Constants
import com.example.soplant.domain.interactors.confirm_reset.ConfirmReset
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

class ConfirmResetDataMiddleware @Inject constructor(private val confirmReset: ConfirmReset): Middleware<ConfirmResetAction, ConfirmResetViewState> {
    @ExperimentalCoroutinesApi
    override suspend fun process(
        currentState: ConfirmResetViewState,
        action: ConfirmResetAction,
        coroutineScope: CoroutineScope
    ): Flow<ConfirmResetAction> = channelFlow {
        when (action) {
            is ConfirmResetAction.ProcessConfirmation -> {
                confirmReset(currentState.newPassword, "${currentState.valueOne}${currentState.valueTwo}${currentState.valueThree}${currentState.valueFour}${currentState.valueFive}${currentState.valueSix}").onEach {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                            send(ConfirmResetAction.ConfirmationStarted)
                        }
                        Resource.Status.ERROR -> {
                            send(ConfirmResetAction.ConfirmationFailed(it.message ?: Constants.General.UNEXPECTED_ERROR))
                            close()
                        }
                        Resource.Status.SUCCESS -> {
                            send(ConfirmResetAction.ConfirmationSuccess)
                            close()
                        }
                    }
                }.launchIn(coroutineScope)
            }
        }
        awaitClose()
    }
}