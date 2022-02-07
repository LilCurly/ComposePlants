package com.example.soplant.presentation.ui.reset_password

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.soplant.R
import com.example.soplant.presentation.theme.Grey
import com.example.soplant.presentation.theme.RedError
import com.example.soplant.presentation.ui.components.BaseButtonComponent
import com.example.soplant.presentation.ui.custom.CustomTextField
import com.example.soplant.presentation.ui.custom.LoadingScreenComposable
import com.example.soplant.presentation.utils.ErrorCodeConverter

@Composable
fun ComposeResetPasswordScreen(
    navController: NavController,
    viewModel: ResetPasswordViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val focusManager = LocalFocusManager.current

    if (state.mustVerifyUser) {
        viewModel.verifyUser()
    }

    if (state.canProcessNextStep) {
        viewModel.navigateToConfirmReset(navController)
    }

    LoadingScreenComposable(isLoading = state.isVerifyingUser) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(26.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Image(painter = painterResource(id = R.drawable.back_arrow), contentDescription = null)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier.size(136.dp, 72.dp)
                )
            }
            Spacer(modifier = Modifier.height(38.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Please enter your email.",
                    color = Grey,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(28.dp))
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                CustomTextField(
                    value = state.email,
                    onValueChange = { viewModel.updateEmail(it) },
                    placeholder = "Enter here",
                    title = "Email",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    errorMessage = if (state.isValidEmail) "" else "Invalid email format"
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = ErrorCodeConverter.convertErrorCodeToMessage(state.errorCode),
                        color = RedError,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.alpha(if (state.errorCode.isNotEmpty()) 1f else 0f)
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    BaseButtonComponent(
                        text = "Next",
                        enabled = state.canContinue,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        viewModel.clickNextButton()
                    }
                }
            }
        }
    }
}