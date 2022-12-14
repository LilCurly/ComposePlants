package com.example.soplant.presentation.ui.confirmation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soplant.presentation.theme.Grey
import com.example.soplant.presentation.theme.GreyLight
import com.example.soplant.presentation.theme.montserrat
import com.example.soplant.presentation.ui.extensions.advancedShadow

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ConfirmInputComponent(value: String, keyboardActions: KeyboardActions, keyboardOptions: KeyboardOptions, onValueChange: (String) -> Unit, onBackspace: () -> Unit) {
    val inter = remember { MutableInteractionSource() }
    val isFocused by inter.collectIsFocusedAsState()
    Card(
        shape = MaterialTheme.shapes.large,
        backgroundColor = MaterialTheme.colors.background,
        border = BorderStroke((0.9).dp, if (isFocused) MaterialTheme.colors.primary else Color.Transparent),
        elevation = 0.dp,
        modifier = Modifier
            .size(42.dp, 45.dp)
            .advancedShadow(
                color = Grey,
                alpha = 0.1f,
                cornersRadius = 0.dp,
                shadowBlurRadius = 20.dp
            )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = TextStyle(color = Grey, fontFamily = montserrat, fontWeight = FontWeight.Medium, fontSize = 13.sp)
                    .copy(textAlign = TextAlign.Center),
                modifier = Modifier
                    .padding(0.dp, 0.dp)
                    .onKeyEvent {
                        if (it.type == KeyEventType.KeyUp) {
                            if (it.key == Key.Backspace) {
                                onBackspace()
                            }
                        }
                        false
                    },
                singleLine = true,
                interactionSource = inter,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                cursorBrush = SolidColor(Color.Transparent),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (value.isEmpty()) {
                            Text(
                                text = "0",
                                color = GreyLight,
                                fontSize = 13.sp,
                                fontFamily = montserrat,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                    innerTextField()
                }
            )
        }
    }
}