package com.example.soplant.presentation.ui.custom

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BaseButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = greenButtonColors(),
        contentPadding = greenButtonContentPadding(),
        elevation = zeroButtonElevation(),
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .height(50.dp)
    ) {
        Text(text = text, style = MaterialTheme.typography.button)
    }
}