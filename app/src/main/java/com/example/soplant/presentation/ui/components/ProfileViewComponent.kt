package com.example.soplant.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.soplant.presentation.theme.Black
import com.example.soplant.presentation.theme.GreenAlpha
import com.example.soplant.presentation.ui.custom.CustomPlaceholderImage
import com.example.soplant.presentation.ui.extensions.advancedShadow

@Composable
fun ProfileViewComponent(
    image: Any?,
    name: String
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Card(
            modifier = Modifier
                .size(38.dp)
                .advancedShadow(
                    color = Black,
                    alpha = 0.1f,
                    cornersRadius = 19.dp,
                    shadowBlurRadius = 20.dp
                ),
            shape = CircleShape,
            backgroundColor = GreenAlpha,
            elevation = 0.dp
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CustomPlaceholderImage(
                    data = image,
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                )
            }
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = name,
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.onBackground,
        )
    }
}