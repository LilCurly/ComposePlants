package com.example.soplant.presentation.ui.create_post.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soplant.presentation.theme.Black
import com.example.soplant.presentation.theme.TestGreen
import com.example.soplant.presentation.ui.components.NumberKeyboardComponent
import com.example.soplant.presentation.ui.create_post.CreatePostViewModel
import com.example.soplant.presentation.ui.extensions.advancedShadow
import com.example.soplant.redux.create_post.CreatePostViewState

@Composable
fun PostUnitaryPriceComponent(viewModel: CreatePostViewModel, state: CreatePostViewState) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = "Enter price",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.primary
        )
        Spacer(modifier = Modifier.height(14.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .advancedShadow(Black, 0.06f, 15.dp, 20.dp),
            shape = MaterialTheme.shapes.large,
            elevation = 0.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(64.dp)
                        .advancedShadow(Black, 0.06f, 15.dp, 20.dp),
                    backgroundColor = TestGreen,
                    shape = RoundedCornerShape(
                        topStart = 15.dp,
                        topEnd = 0.dp,
                        bottomStart = 15.dp,
                        bottomEnd = 0.dp
                    ),
                    elevation = 0.dp
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "€",
                            style = MaterialTheme.typography.button,
                            fontSize = 18.sp,
                            color = MaterialTheme.colors.primary
                        )
                    }
                }
                Text(
                    text = state.plantUnitaryPrice,
                    style = MaterialTheme.typography.button,
                    letterSpacing = 1.7.sp,
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.primary
                )
            }
        }
        Spacer(modifier = Modifier.height(42.dp))
        NumberKeyboardComponent(26, 20) {
            if (it == "-1") {
                viewModel.deleteUnitaryPrice()
            } else {
                viewModel.editUnitaryPrice(it)
            }
        }
    }
}
