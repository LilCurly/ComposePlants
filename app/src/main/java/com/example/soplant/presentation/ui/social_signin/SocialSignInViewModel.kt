package com.example.soplant.presentation.ui.social_signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.soplant.presentation.commons.Screen
import com.example.soplant.presentation.ui.custom.CustomDropDownModel
import com.example.soplant.redux.Store
import com.example.soplant.redux.social_signin.SocialSignInAction
import com.example.soplant.redux.social_signin.SocialSignInViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SocialSignInViewModel @Inject constructor(private val store: Store<SocialSignInViewState, SocialSignInAction>): ViewModel() {
    val state: StateFlow<SocialSignInViewState> = store.state

    init {
        val action = SocialSignInAction.FetchCountries

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateUsername(username: String) {
        val action = SocialSignInAction.UpdatingUsername(username)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateUserImageUrl(userImageUrl: String) {
        val action = SocialSignInAction.UpdatingUserImageUrl(userImageUrl)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateTosState() {
        val action = SocialSignInAction.ClickingTos

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun clickContinue() {
        val action = SocialSignInAction.ClickingContinue

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun navigateToWall(navController: NavController) {
        val action = SocialSignInAction.NavigateToWall

        viewModelScope.launch {
            store.dispatch(action, this)
        }

        navController.navigate(Screen.Wall.route) {
            popUpTo(0) {
                inclusive = true
            }
        }
    }

    fun signOut() {
        val action = SocialSignInAction.ClickingChange

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun navigateToLogin(navController: NavController) {
        val action = SocialSignInAction.NavigateToLogin

        viewModelScope.launch {
            store.dispatch(action, this)
        }

        navController.navigate(Screen.Login.route) {
            popUpTo(0) {
                inclusive = true
            }
        }
    }

    fun selectCountry(newSelectedCountry: CustomDropDownModel?) {
        val action = SocialSignInAction.SelectingCountry(newSelectedCountry)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }
}