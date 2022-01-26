package com.example.soplant.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soplant.R
import com.example.soplant.presentation.theme.GreenAlpha
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

@Composable
fun WalletViewComponent(isLoading: Boolean, value: String) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.width(90.dp)
    ) {
        Box {
            Card(
                modifier = Modifier
                    .size(80.dp, 35.dp)
                    .placeholder(visible = isLoading, highlight = PlaceholderHighlight.shimmer()),
                backgroundColor = GreenAlpha,
                shape = MaterialTheme.shapes.small, elevation = 0.dp
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp, 0.dp, 10.dp, 0.dp)
                ) {
                    if (!isLoading) {
                        val text = "$$value"
                        Text(
                            text = text,
                            style = MaterialTheme.typography.h1,
                            color = MaterialTheme.colors.primary,
                            softWrap = false,
                            overflow = TextOverflow.Clip,
                            fontSize = if (text.count() < 5) 17.sp else (17 - ((text.count() - 5) * 2)).sp
                        )
                    }
                }
            }
            Icon(
                painter = painterResource(id = R.drawable.icon_wallet),
                contentDescription = null,
                tint = MaterialTheme.colors.primary,
                modifier = Modifier
                    .offset((-10).dp, 0.dp)
                    .align(Alignment.CenterStart)
                    .placeholder(visible = isLoading, highlight = PlaceholderHighlight.shimmer())
            )
        }
    }
}