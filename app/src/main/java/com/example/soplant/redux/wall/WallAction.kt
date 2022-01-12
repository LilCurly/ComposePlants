package com.example.soplant.redux.wall

import com.example.soplant.domain.entities.Product
import com.example.soplant.domain.entities.Wallet
import com.example.soplant.redux.Action

sealed class WallAction: Action {
    object ProductLoading: WallAction()
    object ProductLoadingFailed: WallAction()
    data class ProductLoadingSuccess(val products: List<Product>): WallAction()
    object WalletLoading: WallAction()
    object WalletLoadingFailed: WallAction()
    data class WalletLoadingSuccess(val wallet: Wallet): WallAction()
}
