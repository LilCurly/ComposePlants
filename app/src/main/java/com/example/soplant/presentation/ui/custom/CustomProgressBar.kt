package com.example.soplant.presentation.ui.custom

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.soplant.presentation.theme.SoPlantTheme

@Composable
fun CustomProgressBar() {

}

@Preview
@Composable
fun CustomProgressBarPreview() {
    SoPlantTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            CustomProgressBar()
        }
    }
}