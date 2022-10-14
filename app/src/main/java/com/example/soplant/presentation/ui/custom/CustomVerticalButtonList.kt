package com.example.soplant.presentation.ui.custom

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soplant.presentation.theme.GreenAlpha
import com.example.soplant.presentation.theme.GreenVariant
import com.example.soplant.presentation.theme.SoPlantTheme
import com.example.soplant.presentation.ui.extensions.advancedShadow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class CustomVerticalButtonListModel(val title: String, val key: String)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomVerticalButtonList(
    modifier: Modifier,
    title: String,
    values: List<CustomVerticalButtonListModel>,
    onSelect: (String) -> Unit
) {
    val scope = rememberCoroutineScope()

    val isVisible = remember {
        MutableTransitionState(false)
    }

    LaunchedEffect(Unit) {
        isVisible.targetState = true
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Column(modifier = modifier) {
            CustomAnimations.VisibilityAnimation(
                visibleState = isVisible,
                baseDuration = 350,
                baseDelay = 168,
                mult = 0
            ) {
                Text(
                    style = MaterialTheme.typography.h1,
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.primary,
                    text = title
                )
            }
            Spacer(modifier = Modifier.height(25.dp))
            values.forEachIndexed { i, value ->
                val isLast = i == values.size - 1
                var clicked by remember {
                    mutableStateOf(false)
                }
                val animColor by animateColorAsState(
                    targetValue = if (clicked) MaterialTheme.colors.primary else MaterialTheme.colors.background,
                    animationSpec = tween(
                        durationMillis = 500,
                        delayMillis = 0,
                        easing = FastOutSlowInEasing
                    )
                )
                CustomAnimations.VisibilityAnimation(
                    visibleState = isVisible,
                    baseDuration = 350,
                    baseDelay = 150,
                    mult = i + 1
                ) {
                    Card(
                        shape = MaterialTheme.shapes.large,
                        border = BorderStroke(1.dp, GreenAlpha.copy(0.8f)),
                        backgroundColor = animColor,
                        elevation = 0.dp,
                        onClick = {
                            if (isVisible.currentState) {
                                clicked = true
                                isVisible.targetState = false
                                scope.launch {
                                    val delay = 350 + ((168 / 2))
                                    delay(delay.toLong())
                                    onSelect(value.key)
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .advancedShadow(
                                color = GreenVariant,
                                alpha = 0.18f,
                                cornersRadius = 15.dp,
                                shadowBlurRadius = 23.dp
                            )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = value.title,
                                style = MaterialTheme.typography.body1,
                                color = if (clicked) MaterialTheme.colors.background else MaterialTheme.colors.primary
                            )
                        }
                    }
                }
                if (!isLast) {
                    Spacer(modifier = Modifier.height(25.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun CustomVerticalButtonListPreview() {
    SoPlantTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(verticalArrangement = Arrangement.Center) {
                CustomVerticalButtonList(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    title = "Je suis une...",
                    values = listOf(
                        CustomVerticalButtonListModel("Test 1", "test_1"),
                        CustomVerticalButtonListModel("Test 2", "test_2"),
                        CustomVerticalButtonListModel("Test 3", "test_3")
                    )
                ) {}
            }
        }
    }
}