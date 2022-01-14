package com.example.soplant.presentation.ui.wall

import android.graphics.Color
import android.graphics.drawable.shapes.Shape
import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.soplant.R
import com.example.soplant.presentation.ui.login.LoginViewModel
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.soplant.domain.entities.Product
import com.example.soplant.presentation.theme.*
import com.example.soplant.presentation.ui.extensions.advancedShadow

@Composable
fun ComposeWallScreen(
    navController: NavController,
    //viewModel: LoginViewModel = hiltViewModel()
) {
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(26.dp, 26.dp)) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Column {
                ProfileViewComposable(painterResource(R.drawable.splashscreen), name = "Anna K.")
            }
            Column {
                WalletViewComposable("1234")
            }
        }
        Spacer(modifier = Modifier.height(36.dp))
        Row(horizontalArrangement = Arrangement.Start
        ) {
            Text(text = "Posts",
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.padding(15.dp, 0.dp, 0.dp, 0.dp)
            )

        }
        Spacer(modifier = Modifier.height(13.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Products()
            Spacer(modifier = Modifier.height(19.dp))
            Products()
            Spacer(modifier = Modifier.height(19.dp))
            Products()
        }
    }
}



@Composable
fun ProfileViewComposable(
    image: Painter,
    name: String
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(35.dp)
                .clip(CircleShape)
                .border(3.dp, color = GreenAlpha, CircleShape)
        )
        Text(text = name,
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(4.dp, 0.dp, 0.dp, 0.dp)
        )
    }
}

@Composable
fun WalletViewComposable(text: String)
{
    Row(horizontalArrangement = Arrangement.End,
        modifier = Modifier.width(90.dp)
    ) {
        Box {
            Card(
                modifier = Modifier.size(80.dp, 35.dp),
                backgroundColor = GreenAlpha,
                shape = MaterialTheme.shapes.small, elevation = 0.dp
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp, 0.dp, 10.dp, 0.dp)
                ) {
                    val textStyleBody1 = MaterialTheme.typography.h1
                    var textStyle by remember { mutableStateOf(textStyleBody1) }
                    var readyToDraw by remember { mutableStateOf(false) }

                    Text(
                        text = "$$text",
                        style = textStyle,
                        color = MaterialTheme.colors.primary,
                        softWrap = false,
                        overflow = TextOverflow.Clip,
                        modifier = Modifier.drawWithContent {
                            if (readyToDraw) drawContent()
                        },
                        onTextLayout = { textLayoutResult ->
                            if (textLayoutResult.didOverflowHeight) {
                                textStyle = textStyle.copy(fontSize = textStyle.fontSize * 0.9)
                            } else {
                                readyToDraw = true
                            }
                        }
                    )
                }
            }
            Icon(painter = painterResource(id = R.drawable.icon_wallet),
                contentDescription = null,
                tint = MaterialTheme.colors.primary,
                modifier = Modifier
                    .offset((-10).dp, 0.dp)
                    .align(Alignment.CenterStart)
            )
        }
    }
}


@Composable
fun Products()
{
    Card(
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth()
            .advancedShadow(
                color = Grey,
                alpha = 0.3f,
                cornersRadius = 0.dp,
                shadowBlurRadius = 20.dp
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
                    Image(painter = painterResource(R.drawable.splashscreen),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        alpha = 0.8f
                    )
                    Dots()
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
                    Text(text = "Plant Title",
                        style = MaterialTheme.typography.h2,
                        color = MaterialTheme.colors.onSurface
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    ProfileViewReviewsComposable()

                }
                Column(modifier = Modifier.padding(0.dp, 19.dp)) {
                    Text(text = "$10.50",
                        style = MaterialTheme.typography.h1,
                        color = MaterialTheme.colors.primary
                    )
                }
            }
        }
    }
}

@Composable
fun Dots()
{
    Row(modifier = Modifier
        .fillMaxSize()
        .padding(0.dp, 149.dp, 258.dp, 0.dp)
        .alpha(0.85f),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.End
    ){

        Card(
            modifier = Modifier
                .shadow(elevation = 4.dp, shape = CircleShape)
                .size(8.dp)
        ){
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
        ){
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
        ){
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
        ){
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(color = GreyAlternative)
            )
        }
    }
}


@Composable
fun ProfileViewReviewsComposable()
{
    Row(modifier = Modifier.height(27.dp)) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight()
        ) {
            Image(painter = painterResource(R.drawable.splashscreen),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(23.dp)
                    .clip(CircleShape)
            )
        }
        Spacer(modifier = Modifier.size(6.dp))
        Column(modifier = Modifier.fillMaxHeight()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Seller name", style = MaterialTheme.typography.h4)
                Spacer(modifier = Modifier.size(2.dp))
                Icon(painter = painterResource(id = R.drawable.icon_complete),
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier.size(8.dp)
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.star_yellow),
                    contentDescription = null,
                    modifier = Modifier.size(10.dp)
                )
                Spacer(modifier = Modifier.size(2.dp))
                Text(text = "4.5",
                    style = MaterialTheme.typography.caption,
                    color = Grey
                )
            }
        }
    }
}