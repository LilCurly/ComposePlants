package com.example.soplant.redux

interface Reducer<A: Action, S: State> {
    fun reduce(previousState: S, currentAction: A): S
}