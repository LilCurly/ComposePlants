package com.example.soplant.redux

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class Store<S: State, A: Action> @Inject constructor(
    initialState: S,
    private val reducer: Reducer<A, S>,
    private val middlewares: List<@JvmSuppressWildcards Middleware<A, S>> = emptyList()
) {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state

    suspend fun dispatch(action: A, coroutineScope: CoroutineScope) {
        middlewares.forEach {
            it.process(state.value, action, coroutineScope).onEach { newAction ->
                _state.value = reducer.reduce(_state.value, newAction)
            }.launchIn(coroutineScope)
        }

        _state.value = reducer.reduce(_state.value, action)
    }
}