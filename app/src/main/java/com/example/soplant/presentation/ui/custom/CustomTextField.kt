package com.example.soplant.presentation.ui.custom

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soplant.R
import com.example.soplant.presentation.theme.Grey
import com.example.soplant.presentation.theme.GreyLight
import com.example.soplant.presentation.theme.montserrat
import com.example.soplant.presentation.ui.extensions.advancedShadow

@Composable
fun CustomTextField(modifier: Modifier = Modifier, value: String, onValueChange: (String) -> Unit, placeholder: String, title: String, keyboardOptions: KeyboardOptions = KeyboardOptions.Default, keyboardActions: KeyboardActions = KeyboardActions.Default, hideContent: Boolean = false) {
    Card(shape = MaterialTheme.shapes.medium, backgroundColor = MaterialTheme.colors.background, elevation = 0.dp, modifier = modifier
        .height(58.dp)
        .advancedShadow(
            color = Grey,
            alpha = 0.1f,
            cornersRadius = 0.dp,
            shadowBlurRadius = 20.dp
        )) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(19.dp, 10.dp)
        ) {
            var hiddenContentVisibility by remember { mutableStateOf(false) }
            Column(modifier = Modifier
                .fillMaxSize()
                .weight(1f)
            ) {
                val inter = remember { MutableInteractionSource() }
                val isFocused = inter.collectIsFocusedAsState().value
                val transition = updateTransition(isFocused, label = null)
                val titleTextSize by transition.animateFloat(label = "titleTextSize", transitionSpec = {
                    tween(durationMillis = 250, easing = LinearEasing)
                }) {
                    if (!it && value.isEmpty()) 12f else 10f
                }
                val contentTextSize by transition.animateFloat(label = "contentTextSize", transitionSpec = {
                    tween(durationMillis = 250, easing = LinearEasing)
                }) {
                    if (!it && value.isEmpty()) 13f else 15f
                }
                Text(
                    text = title,
                    fontFamily = montserrat,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = titleTextSize.sp,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.weight(1f)
                )
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    textStyle = TextStyle(color = Grey, fontFamily = montserrat, fontWeight = FontWeight.Medium, fontSize = contentTextSize.sp),
                    modifier = Modifier
                        .weight(if (!isFocused && value.isEmpty()) 1f else 2f)
                        .padding(0.dp, 0.dp)
                        .fillMaxWidth()
                        .animateContentSize(
                            animationSpec = tween(
                                durationMillis = 250,
                                easing = LinearEasing
                            )
                        ),
                    singleLine = true,
                    interactionSource = inter,
                    keyboardOptions = keyboardOptions,
                    keyboardActions = keyboardActions,
                    visualTransformation = if (hideContent && !hiddenContentVisibility) PasswordVisualTransformation() else VisualTransformation.None,
                    cursorBrush = SolidColor(MaterialTheme.colors.primary),
                    decorationBox = { innerTextField ->
                        Row(modifier = Modifier.fillMaxWidth()) {
                            if (value.isEmpty()) {
                                Text(
                                    text = placeholder,
                                    color = GreyLight,
                                    fontSize = contentTextSize.sp,
                                    fontFamily = montserrat,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier
                                        .animateContentSize()
                                        .fillMaxWidth()
                                )
                            }
                        }
                        innerTextField()
                    }
                )
            }
            Spacer(modifier = Modifier.width(if (hideContent) 15.dp else 0.dp))
            Column(modifier = Modifier
                .fillMaxHeight()
                .width(if (hideContent) 20.dp else 0.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = { hiddenContentVisibility = !hiddenContentVisibility }) {
                    Image(
                        painter = painterResource(id = if (hiddenContentVisibility) R.drawable.icon_hide else R.drawable.icon_show),
                        contentDescription = null)
                }
            }
        }
    }
}