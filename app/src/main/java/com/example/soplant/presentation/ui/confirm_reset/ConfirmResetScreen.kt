package com.example.soplant.presentation.ui.confirm_reset

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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.soplant.R
import com.example.soplant.presentation.commons.decorators.LoadingScreenDecorator
import com.example.soplant.presentation.theme.Grey
import com.example.soplant.presentation.theme.RedError
import com.example.soplant.presentation.ui.components.BaseButtonComponent
import com.example.soplant.presentation.ui.confirmation.components.OtpComponent
import com.example.soplant.presentation.ui.custom.CustomTextField
import com.example.soplant.presentation.utils.ErrorCodeConverter

@Composable
fun ComposeConfirmResetScreen(
    navController: NavController,
    viewModel: ConfirmResetViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val focusManager = LocalFocusManager.current

    if (state.canProcessConfirm) {
        viewModel.processConfirm()
    }

    if (state.confirmSucceeded) {
        viewModel.navigateToLogin(navController)
    }

    LoadingScreenDecorator(isLoading = state.isLoading) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(26.dp)
        ) {
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
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier.size(136.dp, 72.dp)
                )
            }
            Spacer(modifier = Modifier.height(38.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "We sent a confirmation code on the email you provided.",
                    color = Grey,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(20.dp, 0.dp)
                )
                Text(
                    text = "Please enter it here and your new password below.",
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
                focusManager.moveFocus(FocusDirection.Down)
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                CustomTextField(
                    value = state.newPassword,
                    onValueChange = { viewModel.updateNewPassword(it) },
                    placeholder = "Enter here",
                    title = "New Password",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    hideContent = true
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(11.dp, 0.dp)
            ) {
                Text(
                    text = "Password should contain at least 8 characters, with at least one uppercase character, one lowercase character, one number and one special character.",
                    style = MaterialTheme.typography.caption,
                    color = if (state.isPasswordValid) Grey else RedError
                )
            }
            Spacer(modifier = Modifier.height(25.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = ErrorCodeConverter.convertErrorCodeToMessage(state.errorCode),
                    color = RedError,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.alpha(if (state.errorCode.isNotEmpty()) 1f else 0f)
                )
                Spacer(modifier = Modifier.height(6.dp))
                BaseButtonComponent(
                    text = "Confirm",
                    modifier = Modifier.fillMaxWidth(),
                    enabled = state.canConfirm
                ) {
                    viewModel.confirmClicked()
                }
            }
        }
    }
}