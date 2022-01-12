package com.example.soplant.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private fun loginWithCredentials() {
        val action = LoginAction.LoginClicked

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }
}