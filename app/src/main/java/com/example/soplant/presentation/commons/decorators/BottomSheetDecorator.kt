package com.example.soplant.presentation.commons.decorators

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.soplant.presentation.commons.bottom_sheet.AddImageMethodBottomSheetContent
import com.example.soplant.presentation.theme.Black
import com.example.soplant.presentation.theme.SoPlantTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetDecorator(
    shouldShow: Boolean,
    onClose: () -> Unit,
    bottomSheetContent: @Composable () -> Unit,
    nextComposable: @Composable () -> Unit
) {
    nextComposable()

    val density = LocalDensity.current

    val scope = rememberCoroutineScope()

    val threshold = 250

    var isDragging by remember {
        mutableStateOf(false)
    }

    var offsetY by remember {
        mutableStateOf(0f)
    }

    val offsetAnimation by animateFloatAsState(targetValue = if (!isDragging && offsetY < threshold) 0f else offsetY)

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
            modifier = Modifier.align(Alignment.BottomCenter),
            visible = shouldShow,
            enter = fadeIn() + slideInVertically {
                with(density) { 300.dp.roundToPx() }
            },
            exit = fadeOut() + slideOutVertically {
                with(density) { 300.dp.roundToPx() }
            }) {
            Card(
                modifier = Modifier
                    .offset { IntOffset(y = offsetAnimation.roundToInt(), x = 0) }
                    .fillMaxWidth()
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDrag = { change, dragAmount ->
                                change.consume()
                                isDragging = true

                                if (dragAmount.y > 0) {
                                    offsetY += dragAmount.y
                                } else if (dragAmount.y < 0 && offsetY > 0) {
                                    offsetY += dragAmount.y
                                }
                            },
                            onDragEnd = {
                                isDragging = false
                                if (offsetY >= threshold) {
                                    onClose()
                                    scope.launch {
                                        delay(500)
                                        offsetY = 0f
                                    }
                                } else {
                                    offsetY = 0f
                                }
                            })
                    },
                backgroundColor = MaterialTheme.colors.surface,
                elevation = 0.dp,
                shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, bottom = 24.dp, top = 13.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .height(2.dp)
                                .background(MaterialTheme.colors.primary)
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    bottomSheetContent()
                }
            }
        }
    }
}

@Preview
@Composable
fun BottomSheetDecoratorPreview() {
    SoPlantTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            BottomSheetDecorator(
                shouldShow = true,
                onClose = {},
                bottomSheetContent = { AddImageMethodBottomSheetContent(onTakePicture = {}, onPickGallery = {}) }) {}
        }
    }
}