package com.example.soplant.presentation.ui.wallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soplant.redux.Store
import com.example.soplant.redux.wallet.WalletAction
import com.example.soplant.redux.wallet.WalletViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(private val store: Store<WalletViewState, WalletAction>): ViewModel() {
    val state: StateFlow<WalletViewState> = store.state

    init {
        loadWallet()
        loadTransactions()
    }

    private fun loadWallet() {
        val action = WalletAction.LoadWallet

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    private fun loadTransactions() {
        val action = WalletAction.LoadTransactions

        viewModelScope.launch {
            store.dispatch(action,this)
        }
    }
}