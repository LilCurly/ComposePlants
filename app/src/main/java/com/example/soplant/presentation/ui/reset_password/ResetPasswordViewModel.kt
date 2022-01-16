package com.example.soplant.presentation.ui.reset_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.soplant.redux.Store
import com.example.soplant.redux.reset_password.ResetPasswordAction
import com.example.soplant.redux.reset_password.ResetPasswordViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(private val store: Store<ResetPasswordViewState, ResetPasswordAction>) : ViewModel() {
    val state: StateFlow<ResetPasswordViewState> = store.state

    fun clickNextButton() {
        val action = ResetPasswordAction.ClickedNextButton

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun verifyUser() {
        val action = ResetPasswordAction.ProcessReset

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateEmail(email: String) {
        val action = ResetPasswordAction.UpdatingEmail(email)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun navigateToConfirmReset(navController: NavController) {
        val action = ResetPasswordAction.NavigateToConfirmReset

        viewModelScope.launch {
            store.dispatch(action, this)
        }

        navController.navigate("confirmReset")
    }
}