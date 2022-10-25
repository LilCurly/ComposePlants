package com.example.soplant.presentation.ui.create_post.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                    Image(
                        painter = rememberImagePainter(File(images[0])),
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
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
                        Image(
                            painter = rememberImagePainter(File(images[1])),
                            contentScale = ContentScale.Crop,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
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
                        Image(
                            painter = rememberImagePainter(File(images[2])),
                            contentScale = ContentScale.Crop,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
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
                        Image(
                            painter = rememberImagePainter(File(images[3])),
                            contentScale = ContentScale.Crop,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
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
                        Image(
                            painter = rememberImagePainter(File(images[4])),
                            contentScale = ContentScale.Crop,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
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