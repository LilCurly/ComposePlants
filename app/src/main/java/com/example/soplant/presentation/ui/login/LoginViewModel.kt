package com.example.soplant.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.soplant.redux.Store
import com.example.soplant.redux.login.LoginAction
import com.example.soplant.redux.login.LoginViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val store: Store<LoginViewState, LoginAction>): ViewModel() {
    val state: StateFlow<LoginViewState> = store.state

    fun loginWithCredentials() {
        val action = LoginAction.LoginClicked

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateEmail(newEmail: String) {
        val action = LoginAction.UpdatingUsername(newEmail)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updatePassword(newPassword: String) {
        val action = LoginAction.UpdatingPassword(newPassword)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun navigateToUserWall(navController: NavController) {
        val action = LoginAction.NavigateToUserWall

        viewModelScope.launch {
            store.dispatch(action, this)
        }

        navController.navigate("wall")
    }

    fun navigateToUserValidation(navController: NavController) {
        val action = LoginAction.NavigateToUserValidation

        viewModelScope.launch {
            store.dispatch(action, this)
        }

        navController.navigate("confirmation/${state.value.username}/${state.value.password}")
    }
}