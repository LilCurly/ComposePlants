package com.example.soplant.presentation.commons.decorators

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.soplant.R
import com.example.soplant.presentation.theme.Grey
import com.example.soplant.presentation.theme.SoPlantTheme
import com.example.soplant.presentation.ui.extensions.advancedShadow

@Composable
fun TopBarDecorator(
    navController: NavController?,
    title: String,
    scrollState: LazyListState? = null,
    nextComposable: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 65.dp)
    ) {
        nextComposable()
    }

    val shadowIntensity = 0.12f
    val shadowMaxScroll = 210

    Box {
        Card(
            modifier = Modifier
                .background(MaterialTheme.colors.surface)
                .advancedShadow(
                    color = Grey,
                    alpha = if (scrollState != null)
                        if (scrollState.firstVisibleItemScrollOffset <= shadowMaxScroll) (shadowIntensity - (shadowIntensity * ((shadowMaxScroll - scrollState.firstVisibleItemScrollOffset.toFloat()) / shadowMaxScroll))) else shadowIntensity
                    else
                        0f,
                    shadowBlurRadius = 20.dp
                ),
            elevation = 0.dp,
            shape = RectangleShape
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp)
                        .padding(22.dp)
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.h1,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .widthIn(0.dp, 230.dp)
                    )
                    IconButton(
                        onClick = { navController?.popBackStack() },
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
    }
}

@Preview
@Composable
fun TopBarDecoratorPreview() {
    SoPlantTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            TopBarDecorator(navController = null, title = "Test Top Bar") {

            }
        }
    }
}