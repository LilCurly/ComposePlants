package com.example.soplant.presentation.commons.popup.help

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TestHelpPopup() {
    Text(
        text = "Test title",
        style = MaterialTheme.typography.h1,
        color = MaterialTheme.colors.onSurface
    )
    Spacer(modifier = Modifier.height(25.dp))
    Text(
        text = "Test content",
        style = MaterialTheme.typography.body2,
        color = MaterialTheme.colors.onSurface
    )
}