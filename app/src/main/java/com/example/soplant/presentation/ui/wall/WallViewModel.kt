package com.example.soplant.presentation.ui.wall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soplant.redux.Store
import com.example.soplant.redux.login.LoginAction
import com.example.soplant.redux.login.LoginViewState
import com.example.soplant.redux.wall.WallAction
import com.example.soplant.redux.wall.WallViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WallViewModel @Inject constructor(private val store: Store<WallViewState, WallAction>): ViewModel() {
    val state: StateFlow<WallViewState> = store.state

    private fun loadProducts() {
        val action = WallAction.ShouldLoadProducts

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    private fun clickProducts() {
        /*val action = LoginAction.LoginClicked

        viewModelScope.launch {
            store.dispatch(action, this)
        }*/
    }

    private fun loadWallet() {

    }

    private fun clickWallet() {

    }


}