package com.example.soplant.presentation.ui.confirmation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.soplant.presentation.commons.Screen
import com.example.soplant.redux.Store
import com.example.soplant.redux.confirmation.ConfirmationAction
import com.example.soplant.redux.confirmation.ConfirmationViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ConfirmationViewModel @Inject constructor(private val store: Store<ConfirmationViewState, ConfirmationAction>) :
    ViewModel() {
    val state: StateFlow<ConfirmationViewState> = store.state

    fun confirmClicked() {
        val action = ConfirmationAction.ConfirmClicked

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun resendClicked() {
        val action = ConfirmationAction.ResendClicked

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateValueOne(newValue: String) {
        val action = ConfirmationAction.UpdatingValueOne(newValue)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateValueTwo(newValue: String) {
        val action = ConfirmationAction.UpdatingValueTwo(newValue)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateValueThree(newValue: String) {
        val action = ConfirmationAction.UpdatingValueThree(newValue)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateValueFour(newValue: String) {
        val action = ConfirmationAction.UpdatingValueFour(newValue)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateValueFive(newValue: String) {
        val action = ConfirmationAction.UpdatingValueFive(newValue)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateValueSix(newValue: String) {
        val action = ConfirmationAction.UpdatingValueSix(newValue)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun setUserEmail(userEmail: String) {
        val action = ConfirmationAction.SetUserEmail(userEmail)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun setUserPassword(userPassword: String) {
        val action = ConfirmationAction.SetUserPassword(userPassword)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun navigateToUserWall(navController: NavController) {
        val action = ConfirmationAction.NavigateToUserWall

        viewModelScope.launch {
            store.dispatch(action, this)
        }

        navController.navigate(Screen.Wall.route) {
            popUpTo(0) {
                inclusive = true
            }
        }
    }

    fun navigateToLogin(navController: NavController) {
        val action = ConfirmationAction.NavigateToLogin

        viewModelScope.launch {
            store.dispatch(action, this)
        }

        navController.navigate(Screen.Login.route) {
            popUpTo(0) {
                inclusive = true
            }
        }
    }

    fun authUser() {
        val action = ConfirmationAction.AuthUser

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }
}