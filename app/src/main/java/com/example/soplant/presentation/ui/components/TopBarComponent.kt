package com.example.soplant.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.soplant.R
import com.example.soplant.presentation.theme.Grey
import com.example.soplant.presentation.theme.White
import com.example.soplant.presentation.ui.extensions.advancedShadow

@Composable
fun TopBarComponent(
    navController: NavController,
    title: String,
    scrollState: LazyListState? = null
) {
    Row(
        modifier = Modifier
            .advancedShadow(
                color = Grey,
                alpha = if (scrollState != null)
                    if (scrollState.firstVisibleItemScrollOffset <= 300) (0.08f - (0.08f * ((300f - scrollState.firstVisibleItemScrollOffset.toFloat()) / 300f))) else 0.08f
                else
                    0f,
                shadowBlurRadius = 20.dp
            )
            .background(White)
            .padding(horizontal = 22.dp)
            .fillMaxWidth()
            .height(65.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = title,
                style = MaterialTheme.typography.h1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.Center)
                    .widthIn(0.dp, 230.dp)
            )
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back_arrow),
                    contentDescription = null
                )
            }
        }
    }
}