package com.example.soplant.presentation.ui.custom

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

object CustomAnimations {
    @Composable
    fun VisibilityAnimation(
        visibleState: MutableTransitionState<Boolean>,
        baseDuration: Int,
        baseDelay: Int,
        mult: Int,
        content: @Composable AnimatedVisibilityScope.() -> Unit
    ) {
        val density = LocalDensity.current
        AnimatedVisibility(
            visibleState,
            enter = slideInVertically(animationSpec = tween(baseDuration + (mult * baseDelay), mult * baseDelay, LinearOutSlowInEasing)) {
                with(density) { 40.dp.roundToPx() }
            } + fadeIn(animationSpec = tween(baseDuration + (mult * baseDelay), mult * baseDelay, LinearOutSlowInEasing)),
            exit = slideOutVertically(animationSpec = tween(baseDuration + (mult * (baseDelay / 2)), mult * (baseDelay / 2), LinearOutSlowInEasing)) {
                with(density) { -40.dp.roundToPx() }
            } + fadeOut(animationSpec = tween(baseDuration + (mult * (baseDelay / 2)), mult * (baseDelay / 2), LinearOutSlowInEasing))
        ) {
            content()
        }
    }
}
