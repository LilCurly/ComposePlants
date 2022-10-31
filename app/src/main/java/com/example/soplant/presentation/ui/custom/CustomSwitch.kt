package com.example.soplant.presentation.ui.custom

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soplant.presentation.theme.Grey
import com.example.soplant.presentation.theme.SoPlantTheme
import com.example.soplant.presentation.theme.montserrat
import com.example.soplant.presentation.ui.extensions.advancedShadow

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomSwitch(modifier: Modifier = Modifier, title: String, currentValue: Boolean, onClick: () -> Unit) {
    Column(modifier = modifier) {
        Card(
            shape = MaterialTheme.shapes.large,
            backgroundColor = MaterialTheme.colors.background,
            elevation = 0.dp,
            onClick = { onClick() },
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .advancedShadow(
                    color = Grey,
                    alpha = 0.1f,
                    cornersRadius = 0.dp,
                    shadowBlurRadius = 20.dp
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(19.dp, 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    fontFamily = montserrat,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .weight(1f)
                )
                Text(
                    text = if (currentValue) "Yes" else "No",
                    fontFamily = montserrat,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.primary
                )
                Spacer(modifier = Modifier.width(10.dp))
                CustomSwitchComponent(currentValue = currentValue)
            }
        }
    }
}

@Composable
fun CustomSwitchComponent(currentValue: Boolean) {
    val offsetX by animateDpAsState(targetValue = if (currentValue) 19.dp else 0.dp)
    val backgroundColor by animateColorAsState(targetValue = if (currentValue) MaterialTheme.colors.primary else MaterialTheme.colors.background)
    val foregroundColor by animateColorAsState(targetValue = if (currentValue) MaterialTheme.colors.background else MaterialTheme.colors.primary)

    Row(modifier = Modifier.size(44.dp, 26.dp)) {
        Card(
            modifier = Modifier
                .fillMaxSize(),
            elevation = 0.dp,
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, MaterialTheme.colors.primary),
            backgroundColor = backgroundColor
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding((2.5).dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = Modifier
                        .size(20.dp)
                        .offset(x = offsetX),
                    shape = RoundedCornerShape(10.dp),
                    backgroundColor = foregroundColor,
                    elevation = 0.dp
                ) {

                }
            }
        }
    }
}

@Preview
@Composable
fun CustomSwitchPreview() {
    SoPlantTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            CustomSwitch(
                modifier = Modifier.padding(20.dp),
                title = "Is variegation ?",
                currentValue = false
            ) {

            }
        }
    }
}

@Preview
@Composable
fun CustomSwitchComponentPreview() {
    SoPlantTheme {
        CustomSwitchComponent(currentValue = false)
    }
}
