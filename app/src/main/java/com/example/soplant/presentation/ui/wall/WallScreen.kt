package com.example.soplant.presentation.ui.wall

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.soplant.commons.SharedPreferencesManager
import com.example.soplant.presentation.commons.Screen
import com.example.soplant.presentation.theme.Green
import com.example.soplant.presentation.theme.GreenAlpha
import com.example.soplant.presentation.theme.GreyAlternative
import com.example.soplant.presentation.theme.GreyLight
import com.example.soplant.presentation.ui.components.ProductViewComponent
import com.example.soplant.presentation.ui.components.ProfileViewComponent
import com.example.soplant.presentation.ui.components.WalletViewComponent
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

@Composable
fun ComposeWallScreen(
    navController: NavController,
    viewModel: WallViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val scrollState = rememberLazyListState()

    if (state.shouldCreateUser) {
        navController.navigate(Screen.SocialSignIn.route) {
            popUpTo(0) {
                inclusive = true
            }
        }
    }

    if (state.shouldLoadRemaining) {
        viewModel.loadProducts()
        viewModel.loadWallet()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 26.dp)
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp)
            ) {
                Column {
                    ProfileViewComponent(
                        image = SharedPreferencesManager.shared().getPictureUrl(),
                        name = SharedPreferencesManager.shared().getUserUsername()
                    )
                }
                Column {
                    WalletViewComponent(
                        state.isLoadingWallet,
                        if (state.wallet != null) state.user!!.wallets[0].amount else "0",
                        navController
                    )
                }
            }
            Spacer(modifier = Modifier.height(if (scrollState.firstVisibleItemScrollOffset <= 300) (20 + (16 * ((300f - scrollState.firstVisibleItemScrollOffset.toFloat()) / 300f))).dp else 20.dp))
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Your wall",
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.primary,
                    fontSize = if (scrollState.firstVisibleItemScrollOffset <= 300) (14 + (3 * ((300f - scrollState.firstVisibleItemScrollOffset.toFloat()) / 300f))).sp else 14.sp,
                    modifier = Modifier.padding(41.dp, 0.dp, 0.dp, 0.dp)
                )

            }
            Spacer(modifier = Modifier.height(7.dp))
            Column(
                modifier = Modifier
                    .alpha(if (scrollState.firstVisibleItemScrollOffset <= 300) (1f - (1f * ((300f - scrollState.firstVisibleItemScrollOffset.toFloat()) / 300f))) else 1f)
                    .fillMaxWidth()
                    .height((2).dp)
                    .background(GreenAlpha)
            ) {}
        }
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 26.dp, vertical = 20.dp),
            state = scrollState
        ) {
            if (state.isLoadingProduct) {
                items(3) {
                    Card(
                        elevation = 0.dp,
                        shape = MaterialTheme.shapes.large,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .placeholder(
                                visible = true,
                                highlight = PlaceholderHighlight.shimmer()
                            )
                    ) {}
                    Spacer(modifier = Modifier.height(19.dp))
                }
            } else if (state.products != null && state.products!!.isNotEmpty()) {
                items(state.products?.size ?: 0) { i ->
                    ProductViewComponent(state.products!![i])
                    if (i < state.products!!.size - 1) {
                        Spacer(modifier = Modifier.height(19.dp))
                    }
                }
            } else {
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "No plants found",
                        style = MaterialTheme.typography.subtitle1,
                        color = GreyLight
                    )
                }
            }
        }
    }
}

@Composable
fun Dots() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 149.dp, 258.dp, 0.dp)
            .alpha(0.85f),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.End
    ) {

        Card(
            modifier = Modifier
                .shadow(elevation = 4.dp, shape = CircleShape)
                .size(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(color = Green)
            )
        }
        Spacer(modifier = Modifier.size(2.dp))

        Card(
            modifier = Modifier
                .shadow(4.dp, shape = CircleShape)
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(color = GreyAlternative)
            )
        }
        Spacer(modifier = Modifier.size(2.dp))

        Card(
            modifier = Modifier
                .shadow(4.dp, shape = CircleShape)
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(color = GreyAlternative)
            )
        }
        Spacer(modifier = Modifier.size(2.dp))
        Card(
            modifier = Modifier
                .shadow(4.dp, shape = CircleShape)
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(color = GreyAlternative)
            )
        }
    }
}