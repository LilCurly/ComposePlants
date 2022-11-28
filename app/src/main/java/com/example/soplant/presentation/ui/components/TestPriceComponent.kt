package com.example.soplant.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soplant.presentation.theme.Black
import com.example.soplant.presentation.theme.GreenAlpha
import com.example.soplant.presentation.theme.Grey
import com.example.soplant.presentation.theme.White
import com.example.soplant.presentation.ui.extensions.advancedShadow

@Composable
fun TestPriceComponent() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        val testStateO = remember { mutableStateOf(10) }
        val testStateT = remember { mutableStateOf(0) }
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    modifier = Modifier.weight(4f),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.End
                ) {
                    NumberPicker(
                        state = testStateO,
                        minValue = 1,
                        maxValue = 9999,
                        onStateChanged = {
                            testStateO.value = it.toInt()
                        },
                        textStyle = MaterialTheme.typography.h1.copy(
                            color = MaterialTheme.colors.primary,
                            textAlign = TextAlign.End,
                            fontSize = 20.sp
                        ),
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
                        onStateChanged = {
                            testStateT.value = it.toInt()
                        },
                        textStyle = MaterialTheme.typography.h1.copy(
                            color = MaterialTheme.colors.primary,
                            textAlign = TextAlign.Start,
                            fontSize = 20.sp
                        ),
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
                    shape = RoundedCornerShape(
                        0.dp,
                        15.dp,
                        15.dp,
                        0.dp
                    ),
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
}