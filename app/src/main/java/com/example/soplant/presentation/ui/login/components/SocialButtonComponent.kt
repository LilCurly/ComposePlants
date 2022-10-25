package com.example.soplant.presentation.ui.login.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.soplant.presentation.ui.custom.zeroButtonElevation
import com.example.soplant.presentation.ui.custom.socialContentPadding

@Composable
fun SocialButtonComponent(image: Painter, buttonColors: ButtonColors, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        colors = buttonColors,
        elevation = zeroButtonElevation(),
        contentPadding = socialContentPadding(),
        modifier = Modifier.size(56.dp, 56.dp)
    ) {
        Icon(painter = image, contentDescription = null)
    }
}