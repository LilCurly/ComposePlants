package com.example.soplant.presentation.ui.create_post.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.soplant.R
import com.example.soplant.presentation.theme.Grey
import com.example.soplant.presentation.theme.SoPlantTheme
import com.example.soplant.presentation.ui.custom.addImageButtonColors
import com.example.soplant.presentation.ui.custom.zeroButtonElevation
import com.example.soplant.presentation.ui.custom.zeroContentPadding
import com.example.soplant.presentation.ui.extensions.advancedShadow
import java.io.File

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddImagesComponent(
    images: List<String>,
    onAddClick: () -> Unit,
    onDeleteClick: (Int) -> Unit,
    onEditClick: (Int) -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            Modifier
                .weight(1f)
                .aspectRatio(1f)
        ) {
            Button(
                onClick = { if (images.isNotEmpty()) onEditClick(0) else onAddClick() },
                contentPadding = zeroContentPadding(),
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
                if (images.isNotEmpty()) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = rememberImagePainter(File(images[0])),
                            contentScale = ContentScale.Crop,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                        Card(
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.BottomEnd)
                                .offset(x = (-10).dp, y = (-10).dp),
                            shape = MaterialTheme.shapes.medium,
                            backgroundColor = Color.White,
                            onClick = { onDeleteClick(0) }
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    modifier = Modifier.fillMaxSize(0.5f),
                                    painter = painterResource(id = R.drawable.icon_cross_black),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.icon_add_light),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(0.18f)
                    )
                }
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
                    onClick = { if (images.size >= 2) onEditClick(1) else onAddClick() },
                    elevation = zeroButtonElevation(),
                    contentPadding = zeroContentPadding(),
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
                    if (images.size >= 2) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Image(
                                painter = rememberImagePainter(File(images[1])),
                                contentScale = ContentScale.Crop,
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize()
                            )
                            Card(
                                modifier = Modifier
                                    .size(16.dp)
                                    .align(Alignment.BottomEnd)
                                    .offset(x = (-5).dp, y = (-5).dp),
                                shape = MaterialTheme.shapes.medium,
                                backgroundColor = Color.White,
                                onClick = { onDeleteClick(1) }
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        modifier = Modifier.fillMaxSize(0.5f),
                                        painter = painterResource(id = R.drawable.icon_cross_black),
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.icon_add_light),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(0.3f)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Button(
                    onClick = { if (images.size >= 3) onEditClick(2) else onAddClick() },
                    elevation = zeroButtonElevation(),
                    contentPadding = zeroContentPadding(),
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
                    if (images.size >= 3) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Image(
                                painter = rememberImagePainter(File(images[2])),
                                contentScale = ContentScale.Crop,
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize()
                            )
                            Card(
                                modifier = Modifier
                                    .size(16.dp)
                                    .align(Alignment.BottomEnd)
                                    .offset(x = (-5).dp, y = (-5).dp),
                                shape = MaterialTheme.shapes.medium,
                                backgroundColor = Color.White,
                                onClick = { onDeleteClick(2) }
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        modifier = Modifier.fillMaxSize(0.5f),
                                        painter = painterResource(id = R.drawable.icon_cross_black),
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.icon_add_light),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(0.3f)
                        )
                    }
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
                    onClick = { if (images.size >= 4) onEditClick(3) else onAddClick() },
                    elevation = zeroButtonElevation(),
                    contentPadding = zeroContentPadding(),
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
                    if (images.size >= 4) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Image(
                                painter = rememberImagePainter(File(images[3])),
                                contentScale = ContentScale.Crop,
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize()
                            )
                            Card(
                                modifier = Modifier
                                    .size(16.dp)
                                    .align(Alignment.BottomEnd)
                                    .offset(x = (-5).dp, y = (-5).dp),
                                shape = MaterialTheme.shapes.medium,
                                backgroundColor = Color.White,
                                onClick = { onDeleteClick(3) }
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        modifier = Modifier.fillMaxSize(0.5f),
                                        painter = painterResource(id = R.drawable.icon_cross_black),
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.icon_add_light),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(0.3f)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Button(
                    onClick = { if (images.size == 5) onEditClick(4) else onAddClick() },
                    elevation = zeroButtonElevation(),
                    contentPadding = zeroContentPadding(),
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
                    if (images.size == 5) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Image(
                                painter = rememberImagePainter(File(images[4])),
                                contentScale = ContentScale.Crop,
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize()
                            )
                            Card(
                                modifier = Modifier
                                    .size(16.dp)
                                    .align(Alignment.BottomEnd)
                                    .offset(x = (-5).dp, y = (-5).dp),
                                shape = MaterialTheme.shapes.medium,
                                backgroundColor = Color.White,
                                onClick = { onDeleteClick(4) }
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        modifier = Modifier.fillMaxSize(0.5f),
                                        painter = painterResource(id = R.drawable.icon_cross_black),
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.icon_add_light),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(0.3f)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AddImagesComponentPreview() {
    SoPlantTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            AddImagesComponent(
                images = listOf(),
                onAddClick = {},
                onDeleteClick = {},
                onEditClick = {})
        }
    }
}