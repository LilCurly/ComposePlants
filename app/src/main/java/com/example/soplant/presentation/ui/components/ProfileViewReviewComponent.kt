package com.example.soplant.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.soplant.R
import com.example.soplant.presentation.theme.Grey
import com.example.soplant.presentation.ui.custom.CustomPlaceholderImage

@Composable
fun ProfileViewReviewsComponent(
    sellerName: String,
    sellerReviewAverage: String,
    sellerImageUrl: String,
    isVerified: Boolean
) {
    Row(modifier = Modifier.height(27.dp)) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight()
        ) {
            CustomPlaceholderImage(
                data = if (sellerImageUrl.isNotEmpty()) sellerImageUrl else R.drawable.splashscreen,
                modifier = Modifier
                    .size(23.dp)
                    .clip(CircleShape)
            )
        }
        Spacer(modifier = Modifier.size(6.dp))
        Column(modifier = Modifier.fillMaxHeight()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = sellerName, style = MaterialTheme.typography.h4)
                Spacer(modifier = Modifier.size(2.dp))
                Icon(
                    painter = painterResource(id = R.drawable.icon_complete),
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .size(8.dp)
                        .alpha(if (isVerified) 1f else 0f),
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.star_yellow),
                    contentDescription = null,
                    modifier = Modifier.size(10.dp)
                )
                Spacer(modifier = Modifier.size(2.dp))
                Text(
                    text = sellerReviewAverage,
                    style = MaterialTheme.typography.caption,
                    color = Grey
                )
            }
        }
    }
}