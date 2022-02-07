package com.example.soplant.presentation.ui.create_post

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.soplant.R
import com.example.soplant.presentation.theme.*
import com.example.soplant.presentation.ui.components.BaseButtonComponent
import com.example.soplant.presentation.ui.components.NumberPicker
import com.example.soplant.presentation.ui.components.TopBarComponent
import com.example.soplant.presentation.ui.custom.CustomTextField
import com.example.soplant.presentation.ui.custom.KeyboardSpacer
import com.example.soplant.presentation.ui.custom.addImageButtonColors
import com.example.soplant.presentation.ui.custom.zeroButtonElevation
import com.example.soplant.presentation.ui.extensions.advancedShadow

@Composable
fun ComposeCreatePostScreen(
    navController: NavController
) {
    val scrollState = rememberLazyListState()
    Column(modifier = Modifier.fillMaxSize()) {
        TopBarComponent(
            navController = navController,
            title = "Create Post",
            scrollState = scrollState
        )
        Box(modifier = Modifier
            .weight(1f)
            .fillMaxWidth()) {
            LazyColumn(state = scrollState, modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(start = 26.dp, end = 26.dp, bottom = 40.dp)) {
                item {
                    Row {
                        Spacer(modifier = Modifier.width(14.dp))
                        Text(
                            text = "Add Images",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.primary
                        )
                    }
                    Spacer(modifier = Modifier.height(13.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                        ) {
                            Button(
                                onClick = {},
                                elevation = zeroButtonElevation(),
                                colors = addImageButtonColors(),
                                shape = MaterialTheme.shapes.large,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .advancedShadow(
                                        color = Grey,
                                        alpha = 0.12f,
                                        shadowBlurRadius = 20.dp,
                                        cornersRadius = 15.dp
                                    )
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.icon_add_light),
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize(0.18f)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(
                            Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                        ) {
                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.Top
                            ) {
                                Button(
                                    onClick = {},
                                    elevation = zeroButtonElevation(),
                                    colors = addImageButtonColors(),
                                    shape = MaterialTheme.shapes.large,
                                    modifier = Modifier
                                        .weight(1f)
                                        .aspectRatio(1f)
                                        .advancedShadow(
                                            color = Grey,
                                            alpha = 0.12f,
                                            shadowBlurRadius = 20.dp,
                                            cornersRadius = 15.dp
                                        )
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.icon_add_light),
                                        contentDescription = null,
                                        modifier = Modifier.fillMaxSize(0.3f)
                                    )
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Button(
                                    onClick = {},
                                    elevation = zeroButtonElevation(),
                                    colors = addImageButtonColors(),
                                    shape = MaterialTheme.shapes.large,
                                    modifier = Modifier
                                        .weight(1f)
                                        .aspectRatio(1f)
                                        .advancedShadow(
                                            color = Grey,
                                            alpha = 0.12f,
                                            shadowBlurRadius = 20.dp,
                                            cornersRadius = 15.dp
                                        )
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.icon_add_light),
                                        contentDescription = null,
                                        modifier = Modifier.fillMaxSize(0.3f)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(7.dp))
                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.Bottom
                            ) {
                                Button(
                                    onClick = {},
                                    elevation = zeroButtonElevation(),
                                    colors = addImageButtonColors(),
                                    shape = MaterialTheme.shapes.large,
                                    modifier = Modifier
                                        .weight(1f)
                                        .aspectRatio(1f)
                                        .advancedShadow(
                                            color = Grey,
                                            alpha = 0.12f,
                                            shadowBlurRadius = 20.dp,
                                            cornersRadius = 15.dp
                                        )
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.icon_add_light),
                                        contentDescription = null,
                                        modifier = Modifier.fillMaxSize(0.3f)
                                    )
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Button(
                                    onClick = {},
                                    elevation = zeroButtonElevation(),
                                    colors = addImageButtonColors(),
                                    shape = MaterialTheme.shapes.large,
                                    modifier = Modifier
                                        .weight(1f)
                                        .aspectRatio(1f)
                                        .advancedShadow(
                                            color = Grey,
                                            alpha = 0.12f,
                                            shadowBlurRadius = 20.dp,
                                            cornersRadius = 15.dp
                                        )
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.icon_add_light),
                                        contentDescription = null,
                                        modifier = Modifier.fillMaxSize(0.3f)
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(13.dp))
                    CustomTextField(
                        value = "",
                        onValueChange = {},
                        placeholder = "Enter here",
                        title = "Post Title"
                    )
                    Spacer(modifier = Modifier.height(13.dp))
                    val testStateO = remember { mutableStateOf(10) }
                    val testStateT = remember { mutableStateOf(0) }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Spacer(modifier = Modifier.width(19.dp))
                        Text(
                            text = "Price",
                            style = MaterialTheme.typography.subtitle1,
                            color = MaterialTheme.colors.primary
                        )
                        Spacer(modifier = Modifier.width(15.dp))
                        Card(
                            backgroundColor = White,
                            shape = MaterialTheme.shapes.large,
                            elevation = 0.dp,
                            modifier = Modifier
                                .weight(1f)
                                .height(58.dp)
                                .advancedShadow(
                                    color = Grey,
                                    alpha = 0.12f,
                                    shadowBlurRadius = 20.dp,
                                    cornersRadius = 15.dp
                                )
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxSize()) {
                                Row(
                                    modifier = Modifier.weight(4f),
                                    verticalAlignment = Alignment.Bottom,
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    NumberPicker(
                                        state = testStateO,
                                        minValue = 1,
                                        maxValue = 9999,
                                        onStateChanged = { testStateO.value = it.toInt() },
                                        textStyle = MaterialTheme.typography.h1.copy(color = MaterialTheme.colors.primary, textAlign = TextAlign.End, fontSize = 20.sp),
                                        size = 70.dp,
                                        format = {
                                            var value = it
                                            if (value.isEmpty()) {
                                                value = "0"
                                            }
                                            value
                                        },
                                        maxLength = 4
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = ",",
                                        style = MaterialTheme.typography.subtitle1,
                                        color = Black,
                                        modifier = Modifier.padding(bottom = 6.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    NumberPicker(
                                        state = testStateT,
                                        minValue = 0,
                                        maxValue = 99,
                                        onStateChanged = { testStateT.value = it.toInt() },
                                        textStyle = MaterialTheme.typography.h1.copy(color = MaterialTheme.colors.primary, textAlign = TextAlign.Start, fontSize = 20.sp),
                                        size = 40.dp,
                                        format = {
                                            val value = it.padStart(2, '0')
                                            value
                                        },
                                        maxLength = 2
                                    )
                                }
                                Card(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                        .advancedShadow(
                                            color = Grey,
                                            alpha = 0.1f,
                                            shadowBlurRadius = 15.dp,
                                            cornersRadius = 15.dp
                                        ),
                                    elevation = 0.dp,
                                    shape = RoundedCornerShape(0.dp, 15.dp, 15.dp, 0.dp),
                                    backgroundColor = GreenAlpha
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxSize(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = "€",
                                            style = MaterialTheme.typography.h3,
                                            color = MaterialTheme.colors.primary
                                        )
                                    }
                                }
                            }
                        }
                    }
                    KeyboardSpacer()
                }
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(26.dp)
                .align(Alignment.BottomCenter)) {
                BaseButtonComponent(text = "Continue", modifier = Modifier.fillMaxWidth()) {

                }
            }
        }
    }
}