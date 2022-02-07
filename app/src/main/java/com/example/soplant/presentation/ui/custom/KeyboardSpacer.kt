package com.example.soplant.presentation.ui.custom

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun KeyboardSpacer() {
    val keyboardState by keyboardAsState()
    Spacer(modifier = Modifier.height(if (keyboardState == Keyboard.Opened) 300.dp else 0.dp))
}