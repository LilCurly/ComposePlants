package com.example.soplant.presentation.ui.custom

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.soplant.presentation.theme.*

@Composable
fun lightGreenButtonColors(): ButtonColors = ButtonDefaults.buttonColors(
    backgroundColor = GreenAlpha,
    contentColor = MaterialTheme.colors.primary,
    disabledBackgroundColor = GreyLight,
    disabledContentColor = MaterialTheme.colors.onPrimary
)

@Composable
fun lightGreenButtonContentPadding(): PaddingValues = PaddingValues(
    top = 0.dp,
    start = 15.dp,
    end = 15.dp,
    bottom = 0.dp
)

@Composable
fun variantGreenButtonColors(): ButtonColors = ButtonDefaults.buttonColors(
    backgroundColor = MaterialTheme.colors.primaryVariant,
    contentColor = MaterialTheme.colors.primary,
    disabledBackgroundColor = GreyLight,
    disabledContentColor = MaterialTheme.colors.onPrimary
)

@Composable
fun greenButtonColors(): ButtonColors = ButtonDefaults.buttonColors(
    backgroundColor = MaterialTheme.colors.primary,
    contentColor = MaterialTheme.colors.onPrimary,
    disabledBackgroundColor = GreyTransparent,
    disabledContentColor = MaterialTheme.colors.onPrimary
)

@Composable
fun greenButtonContentPadding(): PaddingValues = PaddingValues(
    top = 10.dp,
    start = 15.dp,
    end = 15.dp,
    bottom = 10.dp
)

@Composable
fun zeroButtonElevation(): ButtonElevation = ButtonDefaults.elevation(
    defaultElevation = 0.dp,
    pressedElevation = 0.dp,
    disabledElevation = 0.dp
)

@Composable
fun zeroContentPadding(): PaddingValues = PaddingValues(
    top = 10.dp,
    start = 15.dp,
    end = 15.dp,
    bottom = 10.dp
)

@Composable
fun googleButtonColors(): ButtonColors = ButtonDefaults.buttonColors(
    backgroundColor = RedGoogle,
    contentColor = White,
    disabledBackgroundColor = Grey,
    disabledContentColor = White
)

@Composable
fun facebookButtonColors(): ButtonColors = ButtonDefaults.buttonColors(
    backgroundColor = BlueFacebook,
    contentColor = White,
    disabledBackgroundColor = Grey,
    disabledContentColor = White
)

