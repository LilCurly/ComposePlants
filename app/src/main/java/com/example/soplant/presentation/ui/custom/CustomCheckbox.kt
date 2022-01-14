package com.example.soplant.presentation.ui.custom

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soplant.presentation.theme.Grey
import com.example.soplant.presentation.ui.extensions.noRippleClickable

@Composable
fun CustomCheckbox(modifier: Modifier = Modifier, text: String, isChecked: Boolean, onSelect: () -> Unit) {
    Row(modifier = modifier.noRippleClickable {
        onSelect()
    }) {
        Card(
            modifier = Modifier.size(20.dp, 20.dp),
            backgroundColor = MaterialTheme.colors.surface,
            shape = RoundedCornerShape(7.dp),
            border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.primary),
            elevation = 0.dp
        ) {
            Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                AnimatedVisibility(
                    visible = isChecked,
                    enter = fadeIn(animationSpec = tween(200)),
                    exit = fadeOut(animationSpec = tween(200))
                ) {
                    Surface(
                        modifier = Modifier.size(12.dp, 12.dp),
                        shape = RoundedCornerShape(5.dp),
                        color = MaterialTheme.colors.primary) { }
                }
            }
        }
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.subtitle1,
            fontSize = 13.sp,
            color = Grey
        )
    }
}