package com.example.soplant.presentation.commons.decorators

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soplant.presentation.theme.Black
import com.example.soplant.presentation.theme.Grey
import com.example.soplant.presentation.ui.extensions.advancedShadow

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HelpScreenDecorator(
    showHelpButton: Boolean,
    popupContent: @Composable () -> Unit,
    nextComposable: @Composable () -> Unit
) {
    nextComposable()

    val density = LocalDensity.current

    var helpPopupOpened by remember { mutableStateOf(false) }
    var helpContentHeight by remember { mutableStateOf(500.dp) }
    var helpMaxHeight by remember { mutableStateOf(0.dp) }
    val helpPopupRadius by animateFloatAsState(
        targetValue = if (helpPopupOpened) 15f else 105f,
        animationSpec = tween(
            if (helpPopupOpened) 385 else 600,
            delayMillis = 75,
            easing = FastOutSlowInEasing
        )
    )
    val helpPopupShadow by animateFloatAsState(targetValue = if (helpPopupOpened) 0f else 0.15f)
    val helpPopupColor by animateColorAsState(
        targetValue = if (helpPopupOpened) MaterialTheme.colors.surface else MaterialTheme.colors.primary.copy(
            0.65f
        ),
        animationSpec = tween(
            600,
            delayMillis = if (helpPopupOpened) 0 else 150,
            easing = FastOutSlowInEasing
        )
    )

    val offsetY by animateDpAsState(
        targetValue = if (helpPopupOpened) -((helpMaxHeight / 2) - (helpContentHeight / 2)) else 145.dp,
        animationSpec = tween(600, easing = FastOutSlowInEasing)
    )
    val offsetX by animateDpAsState(
        targetValue = if (helpPopupOpened) (-25).dp else 145.dp,
        tween(300, easing = FastOutSlowInEasing)
    )

    Box(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(visible = helpPopupOpened, enter = fadeIn(), exit = fadeOut()) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.5f), color = Black, onClick = {
                    helpPopupOpened = false
                }
            ) {}
        }
        AnimatedVisibility(
            visible = showHelpButton,
            enter = fadeIn() + slideInVertically {
                with(density) { 50.dp.roundToPx() }
            } + slideInHorizontally {
                with(density) { 55.dp.roundToPx() }
            },
            exit = fadeOut() + slideOutVertically {
                with(density) { 50.dp.roundToPx() }
            } + slideOutHorizontally {
                with(density) { 55.dp.roundToPx() }
            },
            modifier = Modifier.fillMaxSize()
        ) {
            BoxWithConstraints(modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned {
                    helpMaxHeight = with(density) { it.size.height.toDp() }
                }) {
                Card(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .heightIn(min = if (helpPopupOpened) helpContentHeight else 250.dp)
                        .size(
                            if (helpPopupOpened) maxWidth - 50.dp else 250.dp,
                            if (helpPopupOpened) helpContentHeight else 250.dp
                        )
                        .offset(offsetX, offsetY)
                        .advancedShadow(
                            color = Grey,
                            alpha = helpPopupShadow,
                            cornersRadius = 105.dp,
                            shadowBlurRadius = 20.dp
                        )
                        .animateContentSize(
                            animationSpec = if (helpPopupOpened) tween(
                                700,
                                delayMillis = 300,
                                easing = FastOutSlowInEasing
                            ) else tween(300, delayMillis = 150, easing = FastOutSlowInEasing)
                        ),
                    backgroundColor = helpPopupColor,
                    elevation = 0.dp,
                    onClick = {
                        helpPopupOpened = true
                    },
                    shape = RoundedCornerShape(helpPopupRadius.dp)
                ) {
                    if (helpPopupOpened) {
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(horizontal = 18.dp, vertical = 16.dp)
                            .onGloballyPositioned {
                                helpContentHeight = with(density) { it.size.height.toDp() + 32.dp }
                            }) {
                            popupContent()
                        }
                    } else {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(
                                    start = if (helpPopupOpened) 0.dp else (52.5).dp,
                                    top = if (helpPopupOpened) 0.dp else (47).dp
                                ),
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Column(
                                modifier = Modifier
                                    .wrapContentSize()
                            ) {
                                Text(
                                    text = "?",
                                    style = MaterialTheme.typography.h1,
                                    color = MaterialTheme.colors.onPrimary,
                                    fontSize = 25.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}