package com.example.soplant.presentation.ui.login

import android.widget.Space
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.soplant.R
import com.example.soplant.presentation.theme.Green
import com.example.soplant.presentation.theme.GreenAlpha
import com.example.soplant.presentation.theme.Grey
import com.example.soplant.presentation.theme.montserrat
import com.example.soplant.presentation.ui.custom.*
import com.example.soplant.presentation.ui.extensions.advancedShadow

@Composable
fun ComposeLoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    val focusManager = LocalFocusManager.current

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(26.dp, 26.dp)) {
        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {},
                colors = lightGreenButtonColors(),
                shape = MaterialTheme.shapes.small,
                elevation = zeroButtonElevation(),
                contentPadding = lightGreenButtonContentPadding(),
                modifier = Modifier
                    .height(26.dp)
                    .defaultMinSize(1.dp)
            ) {
                Text(text = "Skip", style = MaterialTheme.typography.subtitle2)
            }
        }
        Spacer(modifier = Modifier.height(19.dp))
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(136.dp, 72.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Welcome :)", color = Grey, style = MaterialTheme.typography.body1)
            Text(
                text = "Please enter your login details",
                color = Grey,
                style = MaterialTheme.typography.body1
            )
        }
        Spacer(modifier = Modifier.height(28.dp))
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            CustomTextField(
                value = state.value.username,
                onValueChange = { viewModel.updateEmail(it) },
                placeholder = "Enter here",
                title = "Email",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )
        }
        Spacer(modifier = Modifier.height(9.dp))
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            CustomTextField(
                value = state.value.password,
                onValueChange = { viewModel.updatePassword(it) },
                placeholder = "Enter here",
                title = "Password",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                hideContent = true
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Forgot password?",
                style = MaterialTheme.typography.caption,
                color = Grey,
                modifier = Modifier.padding(0.dp, 0.dp, 7.dp, 0.dp))
        }
        Spacer(modifier = Modifier.height(27.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {},
                colors = greenButtonColors(),
                contentPadding = greenButtonContentPadding(),
                elevation = zeroButtonElevation(),
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Login", style = MaterialTheme.typography.button)
            }
        }
        Spacer(modifier = Modifier.height(48.dp))
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Or Login Using",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {},
                shape = CircleShape,
                colors = facebookButtonColors(),
                elevation = zeroButtonElevation(),
                contentPadding = zeroContentPadding(),
                modifier = Modifier.size(56.dp, 56.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.icon_facebook), contentDescription = null)
            }
            Spacer(modifier = Modifier.width(40.dp))
            Button(
                onClick = {},
                shape = CircleShape,
                colors = googleButtonColors(),
                elevation = zeroButtonElevation(),
                contentPadding = zeroContentPadding(),
                modifier = Modifier.size(56.dp, 56.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.icon_google), contentDescription = null)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Don’t have an account already? Signup here.",
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}