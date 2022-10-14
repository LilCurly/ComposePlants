package com.example.soplant.presentation.ui.custom

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.soplant.R

@Composable
fun CustomTopBanner(navController: NavController) {
    Row(modifier = Modifier.fillMaxWidth()) {
        IconButton(onClick = {
            navController.popBackStack()
        }) {
            Image(
                painter = painterResource(id = R.drawable.back_arrow),
                contentDescription = null
            )
        }
    }
    Spacer(modifier = Modifier.height(2.dp))
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier.size(111.dp, 59.dp)
        )
    }
}