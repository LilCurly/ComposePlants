package com.example.soplant.presentation.ui.social_signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
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

    fun updateUsername(username: String) {
        val action = SocialSignInAction.UpdatingUsername(username)

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

        navController.navigate("wall")
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

        navController.navigate("login")
    }
}