package com.example.soplant.redux.wallet

import com.example.soplant.commons.Constants
import com.example.soplant.domain.entities.Transaction
import com.example.soplant.domain.entities.TransactionList
import com.example.soplant.domain.entities.Wallet
import com.example.soplant.domain.interactors.wall.GetWallet
import com.example.soplant.domain.interactors.wallet.GetTransactions
import com.example.soplant.domain.utils.Resource
import com.example.soplant.redux.Middleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class WalletDataMiddleware @Inject constructor(
    private val getWallet: GetWallet,
    private val getTransactions: GetTransactions
) : Middleware<WalletAction, WalletViewState> {
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun process(
        currentState: WalletViewState,
        action: WalletAction,
        coroutineScope: CoroutineScope
    ): Flow<WalletAction> = channelFlow {
        when (action) {
            is WalletAction.LoadWallet -> {
                getWallet().onEach {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                            send(WalletAction.WalletLoading)
                        }
                        Resource.Status.ERROR -> {
                            send(WalletAction.WalletLoadingFailed(it.message))
                            close()
                        }
                        Resource.Status.SUCCESS -> {
                            send(
                                WalletAction.WalletLoadingSuccess(
                                    it.data ?: Wallet(
                                        "0",
                                        "0"
                                    )
                                )
                            )
                            close()
                        }
                    }
                }.launchIn(coroutineScope)
            }
            is WalletAction.LoadTransactions -> {
                getTransactions(currentState.lastEvaluatedKey).onEach {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                            send(WalletAction.TransactionsLoading)
                        }
                        Resource.Status.ERROR -> {
                            send(WalletAction.TransactionsLoadingFailed(it.message))
                            close()
                        }
                        Resource.Status.SUCCESS -> {
                            val transactions = mutableListOf<Transaction>()
                            val pendingTransactions = mutableListOf<Transaction>()
                            it.data?.transactions?.forEach { tr ->
                                if (tr.status == Constants.Model.Transaction.Status.FROZEN) {
                                    pendingTransactions.add(tr)
                                } else {
                                    transactions.add(tr)
                                }
                            }
                            send(
                                WalletAction.TransactionsLoadingSuccess(
                                    transactions,
                                    pendingTransactions,
                                    it.data?.lastPaginationId
                                )
                            )
                            close()
                        }
                    }
                }.launchIn(coroutineScope)
            }
            else -> {}
        }
        awaitClose()
    }
}