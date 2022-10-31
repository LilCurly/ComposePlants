package com.example.soplant.presentation.ui.custom

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soplant.R
import com.example.soplant.presentation.theme.*
import com.example.soplant.presentation.ui.extensions.advancedShadow

@Composable
fun CustomMultiLineTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    title: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    hideContent: Boolean = false,
    errorMessage: String = ""
) {
    Column(modifier = modifier) {
        Card(
            shape = MaterialTheme.shapes.large,
            backgroundColor = MaterialTheme.colors.background,
            elevation = 0.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(144.dp)
                .advancedShadow(
                    color = Grey,
                    alpha = 0.1f,
                    cornersRadius = 0.dp,
                    shadowBlurRadius = 20.dp
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(19.dp, 12.dp)
            ) {
                var hiddenContentVisibility by remember { mutableStateOf(false) }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                ) {
                    val inter = remember { MutableInteractionSource() }
                    val isFocused by inter.collectIsFocusedAsState()
                    val transition = updateTransition(isFocused, label = null)
                    val titleTextSize by transition.animateFloat(
                        label = "titleTextSize",
                        transitionSpec = {
                            tween(durationMillis = 250, easing = LinearEasing)
                        }) {
                        if (!it && value.isEmpty()) 12f else 10f
                    }
                    val contentTextSize by transition.animateFloat(
                        label = "contentTextSize",
                        transitionSpec = {
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
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                    BasicTextField(
                        value = value,
                        onValueChange = onValueChange,
                        textStyle = TextStyle(
                            color = Grey,
                            fontFamily = montserrat,
                            fontWeight = FontWeight.Medium,
                            fontSize = contentTextSize.sp
                        ),
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
                        interactionSource = inter,
                        maxLines = 6,
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
                if (hideContent) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        IconButton(onClick = {
                            hiddenContentVisibility = !hiddenContentVisibility
                        }) {
                            Image(
                                painter = painterResource(id = if (hiddenContentVisibility) R.drawable.icon_hide else R.drawable.icon_show),
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(11.dp, 0.dp)
            ) {
                Text(
                    text = errorMessage,
                    style = MaterialTheme.typography.caption,
                    color = RedError
                )
            }
        }
    }
}

@Preview
@Composable
fun CustomMultiLineTextFieldPreview() {
    SoPlantTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            CustomMultiLineTextField(
                modifier = Modifier.padding(20.dp),
                value = "",
                onValueChange = {},
                placeholder = "Enter here",
                title = "Description"
            )
        }
    }
}