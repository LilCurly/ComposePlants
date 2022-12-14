package com.example.soplant.redux.wall

import com.example.soplant.domain.entities.ApiError
import com.example.soplant.domain.entities.Product
import com.example.soplant.domain.entities.User
import com.example.soplant.domain.entities.Wallet
import com.example.soplant.redux.State
import javax.inject.Inject


data class WallViewState(
    val isLoadingUsers: Boolean,
    val isLoadingUser: Boolean,
    val isLoadingProduct: Boolean,
    val products: List<Product>?,
    val lastProductTimestamp: Long?,
    val users: List<User>?,
    val user: User?,
    val shouldCreateUser: Boolean,
    val shouldLoadUsers: Boolean,
    val shouldLoadUser: Boolean,
    val shouldLoadRemaining: Boolean,
    val errorCode: String,
    val error: ApiError?
) : State {
    @Inject
    constructor() : this(false, false, true, null, null, null, null, false, false, false, false, "", null)
}