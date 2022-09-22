package com.example.soplant.redux.register

import com.example.soplant.domain.utils.StringValidators
import com.example.soplant.redux.Middleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterValidatorMiddleware @Inject constructor(private val validator: StringValidators): Middleware<RegisterAction, RegisterViewState> {
    override suspend fun process(
        currentState: RegisterViewState,
        action: RegisterAction,
        coroutineScope: CoroutineScope
    ): Flow<RegisterAction> = flow {
        when (action) {
            is RegisterAction.SignupClicked -> {
                if (!validator.validateEmailFormat(currentState.email)) {
                    emit(RegisterAction.EmailInvalidated)
                } else if (!validator.validatePasswordFormat(currentState.password)) {
                    emit(RegisterAction.PasswordInvalidated)
                } else {
                    emit(RegisterAction.FormValidated)
                }
            }
            else -> {}
        }
    }
}