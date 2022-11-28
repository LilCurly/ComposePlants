package com.example.soplant.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soplant.R
import com.example.soplant.presentation.theme.Black
import com.example.soplant.presentation.ui.custom.whiteButtonColors
import com.example.soplant.presentation.ui.custom.zeroButtonElevation
import com.example.soplant.presentation.ui.extensions.advancedShadow

@Composable
fun NumberKeyboardComponent(parentPadding: Int, innerPadding: Int, onClick: (String) -> Unit) {
    val interItemHorizontalSpacing = 20
    val interItemVerticalSpacing = 20
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val availableHorizontalSpace = screenWidth - ((parentPadding + innerPadding) * 2)
    val cellSize = (availableHorizontalSpace - (interItemHorizontalSpacing * 2)) / 3

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = innerPadding.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            for (i in 1..3) {
                Button(
                    modifier = Modifier
                        .size(cellSize.dp)
                        .advancedShadow(
                            color = Black,
                            alpha = 0.07f,
                            cornersRadius = 15.dp,
                            shadowBlurRadius = 20.dp
                        ),
                    shape = MaterialTheme.shapes.large,
                    colors = whiteButtonColors(),
                    elevation = zeroButtonElevation(),
                    onClick = {
                        onClick(i.toString())
                    }) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = i.toString(),
                            style = MaterialTheme.typography.button,
                            fontSize = 18.sp,
                            color = MaterialTheme.colors.primary
                        )
                    }
                }
                if (i != 3) {
                    Spacer(modifier = Modifier.width(interItemHorizontalSpacing.dp))
                }
            }
        }
        Spacer(modifier = Modifier.height(interItemVerticalSpacing.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            for (i in 4..6) {
                Button(
                    modifier = Modifier
                        .size(cellSize.dp)
                        .advancedShadow(
                            color = Black,
                            alpha = 0.1f,
                            cornersRadius = 15.dp,
                            shadowBlurRadius = 20.dp
                        ),
                    shape = MaterialTheme.shapes.large,
                    colors = whiteButtonColors(),
                    elevation = zeroButtonElevation(),
                    onClick = {
                        onClick(i.toString())
                    }) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = i.toString(),
                            style = MaterialTheme.typography.button,
                            fontSize = 18.sp,
                            color = MaterialTheme.colors.primary
                        )
                    }
                }
                if (i != 6) {
                    Spacer(modifier = Modifier.width(interItemHorizontalSpacing.dp))
                }
            }
        }
        Spacer(modifier = Modifier.height(interItemVerticalSpacing.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            for (i in 7..9) {
                Button(
                    modifier = Modifier
                        .size(cellSize.dp)
                        .advancedShadow(
                            color = Black,
                            alpha = 0.1f,
                            cornersRadius = 15.dp,
                            shadowBlurRadius = 20.dp
                        ),
                    shape = MaterialTheme.shapes.large,
                    colors = whiteButtonColors(),
                    elevation = zeroButtonElevation(),
                    onClick = {
                        onClick(i.toString())
                    }) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = i.toString(),
                            style = MaterialTheme.typography.button,
                            fontSize = 18.sp,
                            color = MaterialTheme.colors.primary
                        )
                    }
                }
                if (i != 9) {
                    Spacer(modifier = Modifier.width(interItemHorizontalSpacing.dp))
                }
            }
        }
        Spacer(modifier = Modifier.height(interItemVerticalSpacing.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            for (i in 0..2) {
                val value = if (i == 0) "." else if (i == 1) "0" else "<-"
                Button(
                    modifier = Modifier
                        .size(cellSize.dp)
                        .advancedShadow(
                            color = Black,
                            alpha = 0.1f,
                            cornersRadius = 15.dp,
                            shadowBlurRadius = 20.dp
                        ),
                    shape = MaterialTheme.shapes.large,
                    colors = whiteButtonColors(),
                    elevation = zeroButtonElevation(),
                    onClick = {
                        if (i == 2) {
                            onClick("-1")
                        } else {
                            onClick(value)
                        }
                    }) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        if (i == 2) {
                            Image(
                                modifier = Modifier.width(29.dp),
                                painter = painterResource(id = R.drawable.delete),
                                colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
                                contentDescription = null
                            )
                        } else {
                            Text(
                                text = value,
                                style = MaterialTheme.typography.button,
                                fontSize = 18.sp,
                                color = MaterialTheme.colors.primary
                            )
                        }
                    }
                }
                if (i != 2) {
                    Spacer(modifier = Modifier.width(interItemHorizontalSpacing.dp))
                }
            }
        }
    }
}