package com.example.soplant.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
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
import com.example.soplant.R
import com.example.soplant.domain.entities.Product
import com.example.soplant.presentation.theme.*
import com.example.soplant.presentation.ui.custom.CustomPlaceholderImage
import com.example.soplant.presentation.ui.extensions.advancedShadow
import com.example.soplant.presentation.ui.wall.Dots
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductViewComponent(product: Product)
{
    Card(
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth()
            .advancedShadow(
                color = Black,
                alpha = 0.17f,
                cornersRadius = 15.dp,
                shadowBlurRadius = 19.dp
            ),
        shape = MaterialTheme.shapes.large,
        elevation = 0.dp,
        backgroundColor = WhiteTransparent

    ) {
        Column(modifier= Modifier.fillMaxSize()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .height(169.dp)
                    .padding(7.dp, 7.dp, 7.dp, 0.dp)
            ) {
                Box(modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .alpha(1f)
                ) {
                    val pagerState = rememberPagerState()
                    HorizontalPager(count = 5, state = pagerState) {
                        CustomPlaceholderImage(
                            data = R.drawable.splashscreen,
                            modifier = Modifier.alpha(0.8f)
                        )
                    }
                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        activeColor = MaterialTheme.colors.primary,
                        inactiveColor = Black.copy(alpha = 0.3f),
                        spacing = 2.dp,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(13.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.size(10.dp))

            Row(modifier = Modifier
                .padding(20.dp, 0.dp)
                .size(323.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(

                ) {
                    Text(text = product.name,
                        style = MaterialTheme.typography.h2,
                        color = MaterialTheme.colors.onSurface
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    ProfileViewReviewsComponent(product.sellerName, product.sellerReviewAverage, product.sellerImageUrl, product.sellerVerified)
                }
                Column(modifier = Modifier.padding(0.dp, 19.dp)) {
                    Text(text = "$${product.unitaryPriceDisplay}",
                        style = MaterialTheme.typography.h1,
                        color = MaterialTheme.colors.primary
                    )
                }
            }
        }
    }
}