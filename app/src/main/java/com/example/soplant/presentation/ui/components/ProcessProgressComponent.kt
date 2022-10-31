package com.example.soplant.presentation.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.soplant.R
import com.example.soplant.presentation.theme.SoPlantTheme
import com.example.soplant.presentation.theme.TestGreen
import kotlin.math.abs

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProcessProgressComponent(
    images: List<Int>,
    currentStep: Int,
    previousStep: Int,
    onClickStep: (Int) -> Unit
) {
    val delay = 300

    val minStep: Int
    val maxStep: Int
    val isNavigatingForward: Boolean

    if (currentStep > previousStep) {
        minStep = previousStep
        maxStep = currentStep
        isNavigatingForward = true
    } else {
        minStep = currentStep
        maxStep = previousStep
        isNavigatingForward = false
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(62.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(62.dp),
            shape = MaterialTheme.shapes.large,
            backgroundColor = TestGreen,
            elevation = 0.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                images.forEachIndexed { i, value ->
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Card(
                            modifier = Modifier.fillMaxSize(0.7f),
                            backgroundColor = Color.Transparent,
                            elevation = 0.dp,
                            onClick = { onClickStep(i) }
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                val delayStep: Int = if (isNavigatingForward) {
                                    abs(minStep - i) * (delay / 2)
                                } else {
                                    (maxStep - i) * delay
                                }
                                val colorStep: Color = if (isNavigatingForward) {
                                    if (i <= maxStep) {
                                        MaterialTheme.colors.primary
                                    } else {
                                        MaterialTheme.colors.background
                                    }
                                } else {
                                    if (i <= minStep) {
                                        MaterialTheme.colors.primary
                                    } else {
                                        MaterialTheme.colors.background
                                    }
                                }
                                val color by animateColorAsState(
                                    targetValue = colorStep,
                                    animationSpec = tween(1000, delayStep)
                                )
                                Image(
                                    modifier = Modifier.size(25.dp),
                                    painter = painterResource(id = value),
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(color)
                                )
                            }
                        }
                    }
                    if (i != 3) {
                        Column(
                            modifier = Modifier
                                .weight(0.5f)
                                .fillMaxHeight(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            val delayStep: Int = if (isNavigatingForward) {
                                abs(minStep - i) * delay
                            } else {
                                (maxStep - i) * (delay / 2)
                            }
                            val colorStep: Color = if (isNavigatingForward) {
                                if (i < maxStep) {
                                    MaterialTheme.colors.primary
                                } else {
                                    MaterialTheme.colors.background
                                }
                            } else {
                                if (i < minStep) {
                                    MaterialTheme.colors.primary
                                } else {
                                    MaterialTheme.colors.background
                                }
                            }
                            val color by animateColorAsState(
                                targetValue = colorStep,
                                animationSpec = tween(1000, delayStep)
                            )
                            Image(
                                modifier = Modifier.rotate(-90f),
                                painter = painterResource(id = R.drawable.icon_arrow_green),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(color)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ProcessProgressComponentPreview() {
    SoPlantTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                ProcessProgressComponent(
                    listOf(
                        R.drawable.icon_bank,
                        R.drawable.icon_bank,
                        R.drawable.icon_bank,
                        R.drawable.icon_bank
                    ), 0, 0
                ) {}
            }
        }
    }
}