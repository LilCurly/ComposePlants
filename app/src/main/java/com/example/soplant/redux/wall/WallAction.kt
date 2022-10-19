package com.example.soplant.redux.wall

import com.example.soplant.domain.entities.ApiError
import com.example.soplant.domain.entities.Product
import com.example.soplant.domain.entities.User
import com.example.soplant.domain.entities.Wallet
import com.example.soplant.redux.Action

sealed class WallAction: Action {
    object LoadProducts: WallAction()
    object ProductLoading: WallAction()
    object LoadUsers: WallAction()
    object UsersLoading: WallAction()
    data class LoadUser(val userId: String): WallAction()
    object UserLoading: WallAction()
    data class ProductLoadingFailed(val errorCode: String?): WallAction()
    data class ProductLoadingSuccess(val products: List<Product>, val lastPaginationId: String?): WallAction()
    object LoadWallet: WallAction()
    object WalletLoading: WallAction()
    data class WalletLoadingFailed(val errorCode: String?): WallAction()
    data class WalletLoadingSuccess(val wallet: Wallet?): WallAction()
    data class UsersLoadingFailed(val errorCode: String?, val error: ApiError?): WallAction()
    data class UsersLoadingSuccess(val users: List<User>?): WallAction()
    data class UserLoadingFailed(val errorCode: String?, val error: ApiError?): WallAction()
    data class UserLoadingSuccess(val user: User?): WallAction()
}
