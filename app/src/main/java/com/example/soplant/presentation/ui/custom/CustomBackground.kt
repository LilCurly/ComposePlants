package com.example.soplant.presentation.ui.custom

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CustomBackground(
    painter: Painter,
    contentDescription: String?,
    shouldShowBottomBar: Boolean,
    content: @Composable () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize().padding(bottom = if(shouldShowBottomBar) 69.dp else 0.dp)) {
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()){}
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(155.dp, 200.dp),
            contentScale = ContentScale.None)
        Surface(color = Color.Transparent, modifier = Modifier.fillMaxSize()) {
            content()
        }
    }
}