package com.example.soplant.redux.wall

import com.example.soplant.domain.entities.Product
import com.example.soplant.domain.entities.Wallet
import com.example.soplant.redux.State
import javax.inject.Inject


data class WallViewState (
    val isLoadingProduct: Boolean,
    val products: List<Product>?,
    val isLoadingWallet: Boolean,
    val wallet: Wallet?,
): State {
    @Inject
    constructor(): this(true, null, true, null)
}