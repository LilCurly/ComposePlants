package com.example.soplant.redux.wall

import com.example.soplant.commons.Constants
import com.example.soplant.redux.Reducer
import javax.inject.Inject

class WallReducer @Inject constructor(): Reducer<WallAction, WallViewState> {
    override fun reduce(previousState: WallViewState, currentAction: WallAction): WallViewState {
        return when(currentAction) {
            is WallAction.ProductLoading -> {
                previousState.copy(isLoadingProduct = true, shouldLoadRemaining = false)
            }
            is WallAction.ProductLoadingFailed -> {
                previousState.copy(isLoadingProduct = false, errorCode = currentAction.errorCode ?: Constants.Error.General.UNEXPECTED_ERROR)
            }
            is WallAction.ProductLoadingSuccess -> {
                previousState.copy(isLoadingProduct = false, products = currentAction.products, lastProductTimestamp = currentAction.lastPaginationId)
            }
            is WallAction.WalletLoading -> {
                previousState.copy(isLoadingWallet = true, shouldLoadRemaining = false)
            }
            is WallAction.WalletLoadingFailed -> {
                previousState.copy(isLoadingWallet = false, errorCode = currentAction.errorCode ?: Constants.Error.General.UNEXPECTED_ERROR)
            }
            is WallAction.WalletLoadingSuccess -> {
                previousState.copy(isLoadingWallet = false, wallet = currentAction.wallet)
            }
            is WallAction.UsersLoading -> {
                previousState.copy(isLoadingUsers = true)
            }
            is WallAction.UserLoading -> {
                previousState.copy(isLoadingUser = true)
            }
            is WallAction.UsersLoadingFailed -> {
                val shouldCreateUser = currentAction.error?.type?.equals(Constants.ApiError.User.DOES_NOT_OWN_ANY_USER)
                previousState.copy(isLoadingUsers = false, errorCode = currentAction.errorCode ?: Constants.Error.General.UNEXPECTED_ERROR, error = currentAction.error, shouldCreateUser = shouldCreateUser ?: true)
            }
            is WallAction.UsersLoadingSuccess -> {
                val shouldCreateUser = currentAction.users?.isEmpty() ?: true
                previousState.copy(isLoadingUsers = false, users = currentAction.users, user = currentAction.users?.first(), shouldCreateUser = shouldCreateUser, shouldLoadRemaining = !shouldCreateUser)
            }
            is WallAction.UserLoadingFailed -> {
                val shouldCreateUser = currentAction.error?.type?.equals(Constants.ApiError.User.DOES_NOT_OWN_ANY_USER)
                previousState.copy(isLoadingUser = false, errorCode = currentAction.errorCode ?: Constants.Error.General.UNEXPECTED_ERROR, error = currentAction.error, shouldCreateUser = shouldCreateUser ?: true)
            }
            is WallAction.UserLoadingSuccess -> {
                val shouldCreateUser = currentAction.user == null
                previousState.copy(isLoadingUser = false, user = currentAction.user, shouldCreateUser = shouldCreateUser, shouldLoadRemaining = !shouldCreateUser)
            }
            is WallAction.LoadProducts, WallAction.LoadWallet, WallAction.LoadUsers -> {
                previousState
            }
            is WallAction.LoadUser -> {
                previousState
            }
        }
    }
}