package com.example.soplant.presentation.ui.custom

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

@Composable
fun CustomPlaceholderImage(data: Any?, modifier: Modifier = Modifier) {
    val loadingFinishedState = remember { mutableStateOf(false) }
    Image(
        painter = rememberImagePainter(
            data = data,
            builder = {
                crossfade(true)
                listener(
                    onCancel = {
                        loadingFinishedState.value = true
                    },
                    onError = { _, _ ->
                        loadingFinishedState.value = true
                    },
                    onSuccess = { _, _ ->
                        loadingFinishedState.value = true
                    }
                )
            }
        ),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier.placeholder(
            visible = !loadingFinishedState.value,
            highlight = PlaceholderHighlight.shimmer()
        )
    )
}