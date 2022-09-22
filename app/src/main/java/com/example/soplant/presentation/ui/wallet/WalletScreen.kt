package com.example.soplant.presentation.ui.wallet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.soplant.R
import com.example.soplant.presentation.commons.Screen
import com.example.soplant.presentation.theme.*
import com.example.soplant.presentation.ui.components.NoRippleComponent
import com.example.soplant.presentation.ui.components.TopBarComponent
import com.example.soplant.presentation.ui.components.TransactionComponent
import com.example.soplant.presentation.ui.custom.greenButtonColors
import com.example.soplant.presentation.ui.custom.zeroButtonElevation
import com.example.soplant.presentation.ui.extensions.advancedShadow
import com.example.soplant.presentation.utils.DecimalConverter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ComposeWalletScreen(
    navController: NavController,
    viewModel: WalletViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        TopBarComponent(navController = navController, title = "Wallet")
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Card(
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.surface,
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)
                    .advancedShadow(
                        color = Grey,
                        alpha = 0.1f,
                        cornersRadius = 15.dp,
                        shadowBlurRadius = 20.dp
                    )
                    .placeholder(
                        visible = state.isLoadingWallet,
                        highlight = PlaceholderHighlight.shimmer()
                    )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(21.dp)
                ) {
                    Text(text = "Available Balance", style = MaterialTheme.typography.body1)
                    Text(
                        text = "$${state.wallet?.availableAmount ?: "0"}",
                        style = MaterialTheme.typography.body1,
                        fontSize = 38.sp,
                        fontFamily = montserrat,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(7.dp))
                    Text(
                        text = "Total Balance $${DecimalConverter.convertPriceString((state.wallet?.availableAmount?.toFloat() ?: 0f) + (state.wallet?.pendingAmount?.toFloat() ?: 0f) + (state.wallet?.frozenAmount?.toFloat() ?: 0f))}",
                        style = MaterialTheme.typography.body2,
                        color = GreyShark
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Button(
                        onClick = { navController.navigate(Screen.AddFund.route) },
                        shape = MaterialTheme.shapes.large,
                        colors = greenButtonColors(),
                        elevation = zeroButtonElevation(),
                        modifier = Modifier
                            .width(137.dp)
                            .height(38.dp)
                    ) {
                        Text(text = "Collect", style = MaterialTheme.typography.button)
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Transactions",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(horizontal = 28.dp, vertical = 8.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            val pages = listOf("Completed", "Pending")
            val pagerState = rememberPagerState()
            var pageIndex by remember { mutableStateOf(0) }
            LaunchedEffect(key1 = pageIndex, block = {
                pagerState.animateScrollToPage(pageIndex)
            })
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(horizontal = 25.dp)
            ) {
                TabRow(
                    selectedTabIndex = pageIndex,
                    indicator = {
                        Box(
                            modifier = Modifier
                                .pagerTabIndicatorOffset(pagerState, it)
                                .padding(horizontal = 28.dp)
                                .height(3.dp)
                                .background(
                                    color = MaterialTheme.colors.primary,
                                    shape = MaterialTheme.shapes.large
                                )
                        )
                    },
                    backgroundColor = Color.Transparent,
                    divider = {}
                ) {
                    NoRippleComponent {
                        pages.forEachIndexed { i, title ->
                            Tab(selected = pageIndex == i, onClick = {
                                pageIndex = i
                            }) {
                                Text(
                                    text = title,
                                    style = MaterialTheme.typography.body2,
                                    color = Grey,
                                    modifier = Modifier.padding(bottom = 5.dp)
                                )
                            }
                        }
                    }
                }
            }
            HorizontalPager(
                count = pages.size, state = pagerState, modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) { page ->
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        top = 10.dp,
                        bottom = 25.dp,
                        start = 25.dp,
                        end = 25.dp
                    )
                ) {
                    if (state.isLoadingTransactions) {
                        item {
                            TransactionComponent(transaction = null)
                            Spacer(modifier = Modifier.height(8.dp))
                            TransactionComponent(transaction = null)
                            Spacer(modifier = Modifier.height(8.dp))
                            TransactionComponent(transaction = null)
                        }
                    } else {
                        if (page == 0) {
                            items(state.transactions?.size ?: 0) { index ->
                                TransactionComponent(transaction = state.transactions?.get(index)!!)
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        } else {
                            items(state.pendingTransactions?.size ?: 0) { index ->
                                TransactionComponent(transaction = state.pendingTransactions?.get(index)!!)
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}