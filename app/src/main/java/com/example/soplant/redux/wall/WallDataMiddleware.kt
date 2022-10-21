package com.example.soplant.redux.wall

import com.example.soplant.domain.interactors.wall.*
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

class WallDataMiddleware @Inject constructor(
    private val getOfflineWall: GetOfflineWall,
    private val getUserWall: GetUserWall,
    private val getUsers: GetUsers,
    private val getUser: GetUser
) : Middleware<WallAction, WallViewState> {
    @ExperimentalCoroutinesApi
    override suspend fun process(
        currentState: WallViewState,
        action: WallAction,
        coroutineScope: CoroutineScope
    ): Flow<WallAction> = channelFlow {
        when (action) {
            is WallAction.LoadProducts -> {
                /*getOfflineWall(0).onEach {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                            send(WallAction.ProductLoading)
                        }
                        Resource.Status.ERROR -> {
                            send(WallAction.ProductLoadingFailed(it.message))
                            close()
                        }
                        Resource.Status.SUCCESS -> {
                            send(WallAction.ProductLoadingSuccess(it.data?.products ?: listOf(), if (it.data?.isLast == true) it.data.lastPaginationId else null))
                            close()
                        }
                    }
                }.launchIn(coroutineScope)*/
                getUserWall(currentState.lastProductTimestamp).onEach {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                            send(WallAction.ProductLoading)
                        }
                        Resource.Status.ERROR -> {
                            send(WallAction.ProductLoadingFailed(it.message))
                            close()
                        }
                        Resource.Status.SUCCESS -> {
                            send(
                                WallAction.ProductLoadingSuccess(
                                    it.data?.products ?: listOf(),
                                    if (it.data?.isLast == true) it.data.lastPaginationTimestamp else null
                                )
                            )
                            close()
                        }
                    }
                }.launchIn(coroutineScope)
            }
            is WallAction.LoadUsers -> {
                getUsers().onEach {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                            send(WallAction.UsersLoading)
                        }
                        Resource.Status.ERROR -> {
                            send(WallAction.UsersLoadingFailed(it.message, it.error))
                            close()
                        }
                        Resource.Status.SUCCESS -> {
                            send(WallAction.UsersLoadingSuccess(it.data))
                            close()
                        }
                    }
                }.launchIn(coroutineScope)
            }
            is WallAction.LoadUser -> {
                getUser(action.userId).onEach {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                            send(WallAction.UserLoading)
                        }
                        Resource.Status.ERROR -> {
                            send(WallAction.UserLoadingFailed(it.message, it.error))
                            close()
                        }
                        Resource.Status.SUCCESS -> {
                            send(WallAction.UserLoadingSuccess(it.data))
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