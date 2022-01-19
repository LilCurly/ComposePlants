package com.example.soplant.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.soplant.commons.Constants
import com.example.soplant.commons.SharedPreferencesManager
import com.example.soplant.presentation.MainActivity
import com.example.soplant.presentation.commons.Screen
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

        navController.navigate(Screen.Wall.route) {
            popUpTo(0) {
                inclusive = true
            }
        }
    }

    fun navigateToUserValidation(navController: NavController) {
        val action = LoginAction.NavigateToUserValidation

        viewModelScope.launch {
            store.dispatch(action, this)
        }

        navController.navigate(Screen.SignUpConfirmation(userEmail = state.value.username, userPassword = state.value.password).route)
    }

    fun signInGoogle(activity: MainActivity) {
        val action = LoginAction.StartSigningProcess

        viewModelScope.launch {
            store.dispatch(action, this)
            activity.federateSignInGoogle(this)
        }
    }

    fun signInFacebook(activity: MainActivity) {
        val action = LoginAction.StartSigningProcess

        viewModelScope.launch {
            store.dispatch(action, this)
            activity.federateSignInFacebook(this)
        }
    }

    fun stopSigningProcess() {
        val action = LoginAction.StopSigningProcess

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun federateSignIn(method: String, navController: NavController) {
        if (method != Constants.SocialSignInMethod.FAILED) {
            if (SharedPreferencesManager.shared().getUserUsername().isEmpty() || SharedPreferencesManager.shared().getUserLocation().isEmpty()) {
                navController.navigate(Screen.SocialSignIn.route) {
                    popUpTo(0) {
                        inclusive = true
                    }
                }
            } else {
                navController.navigate(Screen.Wall.route) {
                    popUpTo(0) {
                        inclusive = true
                    }
                }
            }
        }
    }
}