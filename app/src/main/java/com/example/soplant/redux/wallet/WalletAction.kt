package com.example.soplant.redux.wallet

import com.example.soplant.domain.entities.Transaction
import com.example.soplant.domain.entities.TransactionList
import com.example.soplant.domain.entities.Wallet
import com.example.soplant.redux.Action

sealed class WalletAction : Action {
    object LoadWallet : WalletAction()
    object WalletLoading: WalletAction()
    data class WalletLoadingFailed(val errorCode: String?) : WalletAction()
    data class WalletLoadingSuccess(val wallet: Wallet) : WalletAction()
    object LoadTransactions : WalletAction()
    object TransactionsLoading: WalletAction()
    data class TransactionsLoadingFailed(val errorCode: String?) : WalletAction()
    data class TransactionsLoadingSuccess(
        val transactions: List<Transaction>,
        val pendingTransactions: List<Transaction>,
        val lastEvaluatedKey: String?
    ) : WalletAction()
}