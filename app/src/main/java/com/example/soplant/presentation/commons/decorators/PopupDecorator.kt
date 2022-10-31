package com.example.soplant.presentation.commons.decorators

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.soplant.presentation.commons.popup.PermissionRequestPopupContent
import com.example.soplant.presentation.theme.Black
import com.example.soplant.presentation.theme.SoPlantTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PopupDecorator(
    shouldShow: Boolean,
    onClose: () -> Unit,
    content: @Composable () -> Unit,
    nextComposable: @Composable () -> Unit
) {
    nextComposable()

    val density = LocalDensity.current

    Box(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(visible = shouldShow, enter = fadeIn(), exit = fadeOut()) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.5f), color = Black, onClick = {
                    onClose()
                }
            ) {}
        }
        AnimatedVisibility(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(24.dp),
            visible = shouldShow,
            enter = slideInVertically {
                with(density) { 100.dp.roundToPx() }
            } + fadeIn(),
            exit = slideOutVertically {
                with(density) { 100.dp.roundToPx() }
            } + fadeOut()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                backgroundColor = MaterialTheme.colors.surface,
                elevation = 0.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    content()
                }
            }
        }
    }
}

@Preview
@Composable
fun PopupDecoratorPreview() {
    SoPlantTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            PopupDecorator(
                shouldShow = true,
                onClose = {},
                content = {
                    PermissionRequestPopupContent(
                        "To continue with the current action we must have you permission.",
                        onButtonClick = {})
                }) {

            }
        }
    }
}