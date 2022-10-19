package com.example.soplant.presentation.ui.wall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.soplant.commons.SharedPreferencesManager
import com.example.soplant.redux.Store
import com.example.soplant.redux.wall.WallAction
import com.example.soplant.redux.wall.WallViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WallViewModel @Inject constructor(private val store: Store<WallViewState, WallAction>) :
    ViewModel() {
    val state: StateFlow<WallViewState> = store.state

    init {
        if (SharedPreferencesManager.shared().getLastUserId().isEmpty()) {
            loadUsers()
        } else {
            loadUser(SharedPreferencesManager.shared().getLastUserId())
        }
    }

    private fun loadUsers() {
        val action = WallAction.LoadUsers

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    private fun loadUser(userId: String) {
        val action = WallAction.LoadUser(userId)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun loadProducts() {
        val action = WallAction.LoadProducts

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun loadWallet() {
        val action = WallAction.LoadWallet

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }
}