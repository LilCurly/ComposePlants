package com.example.soplant.presentation.ui.social_signin.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.soplant.R
import com.example.soplant.presentation.theme.Grey
import com.example.soplant.presentation.theme.SilverGrey
import com.example.soplant.presentation.ui.custom.lightGreenButtonColors
import com.example.soplant.presentation.ui.custom.lightGreenButtonContentPadding
import com.example.soplant.presentation.ui.custom.zeroButtonElevation
import com.example.soplant.presentation.ui.extensions.advancedShadow

@Composable
fun ComposeSocialProfileCardComponent(
    userName: String,
    socialMethod: String,
    image: Painter,
    onChangeClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(123.dp)
            .advancedShadow(
                color = Grey,
                alpha = 0.1f,
                cornersRadius = 0.dp,
                shadowBlurRadius = 20.dp
            ),
        shape = MaterialTheme.shapes.large,
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(15.dp, 20.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Logged in as",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.primary
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = image,
                        contentDescription = null,
                        modifier = Modifier
                            .size(45.dp)
                            .clip(CircleShape)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight()) {
                    Text(
                        text = userName,
                        style = MaterialTheme.typography.h3,
                        color = MaterialTheme.colors.onSurface
                    )
                    Text(
                        text = socialMethod,
                        style = MaterialTheme.typography.body2,
                        color = SilverGrey
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Bottom) {
                    Button(
                        onClick = onChangeClicked,
                        colors = lightGreenButtonColors(),
                        shape = MaterialTheme.shapes.small,
                        elevation = zeroButtonElevation(),
                        contentPadding = lightGreenButtonContentPadding(),
                        modifier = Modifier
                            .height(26.dp)
                            .defaultMinSize(1.dp)
                    ) {
                        Text(text = "Change", style = MaterialTheme.typography.subtitle2)
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
    }
}