package com.example.soplant.presentation.ui.custom

import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun customTextFieldColors(): TextFieldColors = TextFieldDefaults.textFieldColors(
    backgroundColor = Color.Red,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
)