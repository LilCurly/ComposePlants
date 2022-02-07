package com.example.soplant.presentation.ui.confirmation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.soplant.R
import com.example.soplant.presentation.theme.Grey
import com.example.soplant.presentation.theme.RedError
import com.example.soplant.presentation.ui.confirmation.components.OtpComponent
import com.example.soplant.presentation.ui.components.BaseButtonComponent
import com.example.soplant.presentation.ui.custom.LoadingScreenComposable
import com.example.soplant.presentation.ui.extensions.noRippleClickable
import com.example.soplant.presentation.utils.ErrorCodeConverter

@Composable
fun ComposeConfirmationScreen(
    navController: NavController,
    userEmail: String,
    userPassword: String,
    viewModel: ConfirmationViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val focusManager = LocalFocusManager.current

    if (state.validationSuccessful) {
        viewModel.authUser()
    }

    if (state.authSucceeded) {
        viewModel.navigateToUserWall(navController)
    }

    if (state.authFailed) {
        viewModel.navigateToLogin(navController)
    }

    if (state.email.isEmpty() && state.password.isEmpty()) {
        viewModel.setUserEmail(userEmail)
        viewModel.setUserPassword(userPassword)
    }

    LoadingScreenComposable(isLoading = state.isLoading) {
        Column(
            modifier = Modifier
                .fillMaxSize()
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
                    text = "We sent you a confirmation code on your email. Please enter it here.",
                    color = Grey,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(20.dp, 0.dp)
                )
            }
            Spacer(modifier = Modifier.height(35.dp))
            OtpComponent(
                focusManager = focusManager,
                valueOne = state.valueOne,
                updateValueOne = { viewModel.updateValueOne(it) },
                valueTwo = state.valueTwo,
                updateValueTwo = { viewModel.updateValueTwo(it) },
                valueThree = state.valueThree,
                updateValueThree = { viewModel.updateValueThree(it) },
                valueFour = state.valueFour,
                updateValueFour = { viewModel.updateValueFour(it) },
                valueFive = state.valueFive,
                updateValueFive = { viewModel.updateValueFive(it) },
                valueSix = state.valueSix,
                updateValueSix = { viewModel.updateValueSix(it) }
            ) {
                viewModel.confirmClicked()
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .noRippleClickable {
                    viewModel.resendClicked()
                },
                horizontalArrangement = Arrangement.Center) {
                Text(text = "Didn't receive? Resend.", style = MaterialTheme.typography.body1, color = MaterialTheme.colors.primary)
            }
            Spacer(modifier = Modifier.height(55.dp))
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
                        text = "Confirm & Finish",
                        enabled = state.canConfirm,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        viewModel.confirmClicked()
                    }
                }
            }
        }
    }
}