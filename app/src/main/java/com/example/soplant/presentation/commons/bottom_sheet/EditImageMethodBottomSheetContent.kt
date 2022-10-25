package com.example.soplant.presentation.commons.bottom_sheet

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.soplant.presentation.ui.custom.zeroButtonElevation

@Composable
fun EditImageMethodBottomSheetContent(onTakePicture: () -> Unit, onPickGallery: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "How would you like to provide a picture ?", modifier = Modifier.padding(horizontal = 20.dp), style = MaterialTheme.typography.h1, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(13.dp))
        Text(text = "Choose a method to replace current picture", style = MaterialTheme.typography.body1, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(24.dp))
        Button(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp), onClick = { onTakePicture() }, elevation = zeroButtonElevation()
        ) {
            Text(text = "Take a picture", style = MaterialTheme.typography.button)
        }
        Spacer(modifier = Modifier.height(13.dp))
        Button(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp), onClick = { onPickGallery() }, elevation = zeroButtonElevation()
        ) {
            Text(text = "Choose from Gallery", style = MaterialTheme.typography.button)
        }
    }
}