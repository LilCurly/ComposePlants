package com.example.soplant.presentation.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.soplant.presentation.ui.custom.greenButtonColors
import com.example.soplant.presentation.ui.custom.greenButtonContentPadding
import com.example.soplant.presentation.ui.custom.zeroButtonElevation

@Composable
fun BaseButtonComponent(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = greenButtonColors(),
        contentPadding = greenButtonContentPadding(),
        elevation = zeroButtonElevation(),
        shape = MaterialTheme.shapes.large,
        enabled = enabled,
        modifier = modifier
            .height(50.dp)
    ) {
        Text(text = text, style = MaterialTheme.typography.button)
    }
}