package com.example.soplant.presentation.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.soplant.R
import com.example.soplant.presentation.theme.Green
import com.example.soplant.presentation.theme.GreenAlpha
import com.example.soplant.presentation.theme.Grey

@Composable
fun ComposeLoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(26.dp, 26.dp)) {
        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
            Card(
                modifier = Modifier.size(53.dp, 26.dp),
                backgroundColor = GreenAlpha,
                shape = MaterialTheme.shapes.small, elevation = 0.dp) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
                    Text(text = "Skip", color = MaterialTheme.colors.primary, style = MaterialTheme.typography.subtitle2)
                }
            }
        }
        Spacer(modifier = Modifier.height(19.dp))
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Image(painter = painterResource(id = R.drawable.logo), contentDescription = null, modifier = Modifier.size(136.dp, 72.dp))
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Welcome :)", color = Grey, style = MaterialTheme.typography.body1)
            Text(text = "Please enter your login details", color = Grey, style = MaterialTheme.typography.body1)
        }
        Spacer(modifier = Modifier.height(28.dp))

    }
}