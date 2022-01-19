package com.example.soplant.presentation.ui.custom

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.focus.*
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.soplant.R
import com.example.soplant.presentation.theme.*
import com.example.soplant.presentation.ui.extensions.advancedShadow

data class CustomDropDownModel(val name: String, val imageUrl: String? = null, val code: String)

@Composable
fun CustomDropDown(modifier: Modifier = Modifier, values: List<CustomDropDownModel>, selectedValue: CustomDropDownModel?, title: String, placeholder: String, isLoading: Boolean, focusManager: FocusManager, onSelectChanged: (CustomDropDownModel?) -> Unit) {
    var typedValue by remember { mutableStateOf(selectedValue?.name ?: "") }
    val inter = remember { MutableInteractionSource() }
    val requester = FocusRequester()
    var filteredValues by remember { mutableStateOf(values) }
    val isFocused by inter.collectIsFocusedAsState()
    val transition = updateTransition(isFocused, label = null)
    Column(modifier = modifier.advancedShadow(
        color = Grey,
        alpha = 0.1f,
        cornersRadius = 0.dp,
        shadowBlurRadius = 20.dp
    )) {
        Card(
            shape = RoundedCornerShape(15.dp, 15.dp, if (!isFocused || (filteredValues.isEmpty() && values.isNotEmpty())) 15.dp else 0.dp, if (!isFocused || (filteredValues.isEmpty() && values.isNotEmpty())) 15.dp else 0.dp),
            backgroundColor = MaterialTheme.colors.background,
            elevation = 0.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
        ) {
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(19.dp, 10.dp)
            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                ) {
                    val titleTextSize by transition.animateFloat(label = "titleTextSize", transitionSpec = {
                        tween(durationMillis = 250, easing = LinearEasing)
                    }) {
                        if (!it && typedValue.isEmpty()) 12f else 10f
                    }
                    val contentTextSize by transition.animateFloat(label = "contentTextSize", transitionSpec = {
                        tween(durationMillis = 250, easing = LinearEasing)
                    }) {
                        if (!it && typedValue.isEmpty()) 13f else 15f
                    }
                    Text(
                        text = title,
                        fontFamily = montserrat,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = titleTextSize.sp,
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier.weight(1f)
                    )
                    Row(
                        modifier = Modifier.weight(if (!isFocused && typedValue.isEmpty()) 1f else 2f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(if (selectedValue?.imageUrl != null) 15.dp else 0.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Card(
                                shape = RoundedCornerShape(3.dp),
                                modifier = Modifier
                                    .width(15.dp)
                                    .height(10.dp)
                                    .advancedShadow(
                                        color = Grey,
                                        alpha = 0.15f,
                                        cornersRadius = 1.dp,
                                        shadowBlurRadius = 5.dp
                                    ),
                                elevation = 0.dp
                            ) {
                                Image(
                                    painter = rememberImagePainter(selectedValue?.imageUrl),
                                    contentDescription = null,
                                    contentScale = ContentScale.FillBounds
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(if (selectedValue?.imageUrl != null) 10.dp else 0.dp))
                        BasicTextField(
                            value = typedValue,
                            onValueChange = {
                                onSelectChanged(null)
                                filteredValues = values.filter { el ->
                                    el.name.startsWith(it, true)
                                }
                                typedValue = it
                            },
                            textStyle = TextStyle(color = Grey, fontFamily = montserrat, fontWeight = FontWeight.Medium, fontSize = contentTextSize.sp),
                            modifier = Modifier
                                .weight(1f)
                                .padding(0.dp, 0.dp)
                                .animateContentSize(
                                    animationSpec = tween(
                                        durationMillis = 250,
                                        easing = LinearEasing
                                    )
                                )
                                .focusRequester(requester)
                                .onFocusChanged {
                                    if (it.hasFocus) {
                                        filteredValues = values.filter { el ->
                                            el.name.startsWith(typedValue)
                                        }
                                    }
                                },
                            singleLine = true,
                            interactionSource = inter,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = { focusManager.moveFocus(FocusDirection.Down) }
                            ),
                            cursorBrush = SolidColor(MaterialTheme.colors.primary),
                            decorationBox = { innerTextField ->
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    if (typedValue.isEmpty()) {
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
                }
                Spacer(modifier = Modifier.width(15.dp))
                Column(modifier = Modifier
                    .fillMaxHeight()
                    .width(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    val rotationDegree by transition.animateFloat(label = "rotationDegree", transitionSpec = {
                        tween(durationMillis = 500, easing = FastOutSlowInEasing)
                    }) {
                        if (it) 180f else 0f
                    }
                    IconButton(onClick = { if (isFocused) focusManager.clearFocus(true) else requester.requestFocus()}) {
                        Image(
                            painter = painterResource(id = R.drawable.icon_arrow_green),
                            modifier = Modifier.rotate(rotationDegree),
                            contentDescription = null
                        )
                    }
                }
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clipToBounds(),
            backgroundColor = MaterialTheme.colors.background,
            shape = RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp),
            elevation = 0.dp
        ) {
            if (!isLoading && values.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .heightIn(0.dp, if (isFocused) 200.dp else 0.dp)
                        .fillMaxWidth()
                        .animateContentSize()
                ) {
                    items(filteredValues.size) { index ->
                        val value = filteredValues[index]
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(if (value.code == selectedValue?.code) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.surface)
                            .clickable {
                                typedValue = value.name
                                focusManager.clearFocus(true)
                                onSelectChanged(value)
                            }) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(10.dp)) {
                                if (value.imageUrl != null) {
                                    Column(modifier = Modifier
                                        .fillMaxHeight()
                                        .width(50.dp),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Card(
                                            modifier = Modifier
                                                .width(35.dp)
                                                .height(30.dp)
                                                .advancedShadow(
                                                    color = Grey,
                                                    alpha = 0.15f,
                                                    cornersRadius = 0.dp,
                                                    shadowBlurRadius = 12.dp
                                                ),
                                            shape = RoundedCornerShape(7.dp),
                                            elevation = 0.dp,
                                        ) {
                                            Image(
                                                painter = rememberImagePainter(value.imageUrl),
                                                contentDescription = null,
                                                contentScale = ContentScale.FillBounds,
                                                modifier = Modifier.fillMaxSize()
                                            )
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.width(if (value.imageUrl != null) 15.dp else 0.dp))
                                Column(modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = value.name,
                                        style = MaterialTheme.typography.subtitle1,
                                        color = MaterialTheme.colors.primary
                                    )
                                }
                            }
                            if (index < filteredValues.size - 1) {
                                Column(modifier = Modifier
                                    .fillMaxWidth()
                                    .height((0.5).dp)
                                    .background(
                                        MaterialTheme.colors.primaryVariant
                                    )
                                    .alpha(0.1f)) {}
                            }
                        }
                    }
                }
            } else if (isLoading && isFocused) {
                Column(modifier = Modifier
                    .height(35.dp)
                    .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(15.dp))
                }
            } else if(isFocused) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "There is no value to display.",
                        style = MaterialTheme.typography.subtitle2,
                        color = MaterialTheme.colors.primary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(15.dp)
                    )
                }
            }
        }
    }
}