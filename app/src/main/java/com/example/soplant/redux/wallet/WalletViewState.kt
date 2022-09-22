package com.example.soplant.redux.wallet

import com.example.soplant.domain.entities.Transaction
import com.example.soplant.domain.entities.Wallet
import com.example.soplant.redux.State
import javax.inject.Inject

data class WalletViewState (
    val isLoadingWallet: Boolean,
    val wallet: Wallet?,
    val isLoadingTransactions: Boolean,
    val transactions: List<Transaction>?,
    val pendingTransactions: List<Transaction>?,
    val lastEvaluatedKey: String?,
    val errorCode: String?
): State {
    @Inject
    constructor(): this(true, null, true, null, null, null, null)
}