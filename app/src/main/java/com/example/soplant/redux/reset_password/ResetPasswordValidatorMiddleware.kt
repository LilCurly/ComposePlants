package com.example.soplant.redux.reset_password

import com.example.soplant.domain.utils.StringValidators
import com.example.soplant.redux.Middleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ResetPasswordValidatorMiddleware @Inject constructor(private val validator: StringValidators): Middleware<ResetPasswordAction, ResetPasswordViewState> {
    override suspend fun process(
        currentState: ResetPasswordViewState,
        action: ResetPasswordAction,
        coroutineScope: CoroutineScope
    ): Flow<ResetPasswordAction> = flow {
        when (action) {
            is ResetPasswordAction.ClickedNextButton -> {
                if (!validator.validateEmailFormat(currentState.email)) {
                    emit(ResetPasswordAction.EmailInvalidated)
                } else {
                    emit(ResetPasswordAction.FormValidated)
                }
            }
            else -> {}
        }
    }
}