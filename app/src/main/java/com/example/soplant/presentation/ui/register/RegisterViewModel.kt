package com.example.soplant.presentation.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.soplant.redux.Store
import com.example.soplant.redux.register.RegisterAction
import com.example.soplant.redux.register.RegisterViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val store: Store<RegisterViewState, RegisterAction>): ViewModel() {
    val state: StateFlow<RegisterViewState> = store.state

    fun signupUser() {
        val action = RegisterAction.ProcessSignup

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun validateForm() {
        val action = RegisterAction.SignupClicked

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateEmail(newEmail: String) {
        val action = RegisterAction.UpdatingEmail(newEmail)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateUsername(newUsername: String) {
        val action = RegisterAction.UpdatingUsername(newUsername)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updatePassword(newPassword: String) {
        val action = RegisterAction.UpdatingPassword(newPassword)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateTosChecked(newStatus: Boolean) {
        val action = RegisterAction.ClickedReadTos(newStatus)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun navigateToConfirmationScreen(navController: NavController) {
        val action = RegisterAction.NavigateToConfirmationScreen

        viewModelScope.launch {
            store.dispatch(action, this)
        }

        navController.navigate("confirmation/${state.value.email}/${state.value.password}")
    }
}