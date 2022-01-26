package com.example.soplant.redux.wall

import com.example.soplant.commons.Constants
import com.example.soplant.redux.Reducer
import javax.inject.Inject

class WallReducer @Inject constructor(): Reducer<WallAction, WallViewState> {
    override fun reduce(previousState: WallViewState, currentAction: WallAction): WallViewState {
        return when(currentAction) {
            is WallAction.ProductLoading -> {
                previousState.copy(isLoadingProduct = true)
            }
            is WallAction.ProductLoadingFailed -> {
                previousState.copy(isLoadingProduct = false, errorCode = currentAction.errorCode ?: Constants.Error.General.UNEXPECTED_ERROR)
            }
            is WallAction.ProductLoadingSuccess -> {
                previousState.copy(isLoadingProduct = false, products = currentAction.products, lastProductTimestamp = currentAction.lastPaginationId)
            }
            is WallAction.WalletLoading -> {
                previousState.copy(isLoadingWallet = true)
            }
            is WallAction.WalletLoadingFailed -> {
                previousState.copy(isLoadingWallet = false, errorCode = currentAction.errorCode ?: Constants.Error.General.UNEXPECTED_ERROR)
            }
            is WallAction.WalletLoadingSuccess -> {
                previousState.copy(isLoadingWallet = false, wallet = currentAction.wallet)
            }
            is WallAction.LoadProducts, WallAction.LoadWallet -> {
                previousState
            }
        }
    }
}