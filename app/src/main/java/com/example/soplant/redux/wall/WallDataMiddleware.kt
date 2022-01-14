package com.example.soplant.redux.wall

import com.example.soplant.domain.interactors.wall.GetOfflineWall
import com.example.soplant.redux.Middleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class WallDataMiddleware @Inject constructor(val getOfflineWall: GetOfflineWall): Middleware<WallAction, WallViewState> {
    override suspend fun process(
        currentState: WallViewState,
        action: WallAction,
        coroutineScope: CoroutineScope
    ): Flow<WallAction> = channelFlow {
        when (action) {
            is WallAction.ShouldLoadProducts -> {
                getOfflineWall(currentState.lastProductTimestamp ?: 0).onEach {

                }
            }
        }
    }

}