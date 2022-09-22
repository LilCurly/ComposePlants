package com.example.soplant.redux.wallet

import com.example.soplant.redux.Reducer
import javax.inject.Inject

class WalletReducer @Inject constructor() : Reducer<WalletAction, WalletViewState> {
    override fun reduce(
        previousState: WalletViewState,
        currentAction: WalletAction
    ): WalletViewState {
        return when (currentAction) {
            is WalletAction.TransactionsLoading -> {
                previousState.copy(isLoadingTransactions = true)
            }
            is WalletAction.WalletLoading -> {
                previousState.copy(isLoadingWallet = true)
            }
            is WalletAction.WalletLoadingFailed -> {
                previousState.copy(isLoadingWallet = false, errorCode = currentAction.errorCode)
            }
            is WalletAction.WalletLoadingSuccess -> {
                previousState.copy(isLoadingWallet = false, wallet = currentAction.wallet)
            }
            is WalletAction.TransactionsLoadingFailed -> {
                previousState.copy(
                    isLoadingTransactions = false,
                    errorCode = currentAction.errorCode
                )
            }
            is WalletAction.TransactionsLoadingSuccess -> {
                previousState.copy(
                    isLoadingTransactions = false,
                    transactions = currentAction.transactions,
                    pendingTransactions = currentAction.pendingTransactions,
                    lastEvaluatedKey = currentAction.lastEvaluatedKey
                )
            }
            is WalletAction.LoadWallet, WalletAction.LoadTransactions -> {
                previousState
            }
        }
    }
}