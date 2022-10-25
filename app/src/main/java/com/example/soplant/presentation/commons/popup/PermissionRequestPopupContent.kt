package com.example.soplant.presentation.commons.popup

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.soplant.presentation.ui.custom.zeroButtonElevation

@Composable
fun PermissionRequestPopupContent(content: String, onButtonClick: () -> Unit) {
    Text(modifier = Modifier.fillMaxWidth(), text = "We need your permission!", style = MaterialTheme.typography.h1, textAlign = TextAlign.Center)
    Spacer(modifier = Modifier.height(13.dp))
    Text(modifier = Modifier.fillMaxWidth(), text = content, style = MaterialTheme.typography.body1, textAlign = TextAlign.Center)
    Spacer(modifier = Modifier.height(24.dp))
    Button(modifier = Modifier.fillMaxWidth().height(50.dp),onClick = { onButtonClick() }, elevation = zeroButtonElevation()) {
        Text(text = "Request permission", style = MaterialTheme.typography.button)
    }
}