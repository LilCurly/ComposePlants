package com.example.soplant.presentation.ui.confirm_reset

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.soplant.redux.Store
import com.example.soplant.redux.confirm_reset.ConfirmResetAction
import com.example.soplant.redux.confirm_reset.ConfirmResetViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfirmResetViewModel @Inject constructor(private val store: Store<ConfirmResetViewState, ConfirmResetAction>): ViewModel() {
    val state: StateFlow<ConfirmResetViewState> = store.state

    fun confirmClicked() {
        val action = ConfirmResetAction.ClickedConfirm

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateNewPassword(newPassword: String) {
        val action = ConfirmResetAction.UpdateNewPassword(newPassword)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateValueOne(value: String) {
        val action = ConfirmResetAction.UpdateValueOne(value)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateValueTwo(value: String) {
        val action = ConfirmResetAction.UpdateValueTwo(value)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateValueThree(value: String) {
        val action = ConfirmResetAction.UpdateValueThree(value)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateValueFour(value: String) {
        val action = ConfirmResetAction.UpdateValueFour(value)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateValueFive(value: String) {
        val action = ConfirmResetAction.UpdateValueFive(value)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateValueSix(value: String) {
        val action = ConfirmResetAction.UpdateValueSix(value)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun processConfirm() {
        val action = ConfirmResetAction.ProcessConfirmation

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun navigateToLogin(navController: NavController) {
        val action = ConfirmResetAction.NavigateToLogin

        viewModelScope.launch {
            store.dispatch(action, this)
        }

        navController.navigate("login")
    }
}