package com.example.soplant.redux.confirm_reset

import com.example.soplant.domain.utils.StringValidators
import com.example.soplant.redux.Middleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ConfirmResetValidatorMiddleware @Inject constructor(private val validator: StringValidators): Middleware<ConfirmResetAction, ConfirmResetViewState> {
    override suspend fun process(
        currentState: ConfirmResetViewState,
        action: ConfirmResetAction,
        coroutineScope: CoroutineScope
    ): Flow<ConfirmResetAction> = flow {
        when (action) {
            is ConfirmResetAction.ClickedConfirm -> {
                if (!validator.validatePasswordFormat(currentState.newPassword)) {
                    emit(ConfirmResetAction.PasswordInvalidated)
                } else {
                    emit(ConfirmResetAction.FormValidated)
                }
            }
        }
    }
}