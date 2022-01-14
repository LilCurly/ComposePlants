package com.example.soplant.presentation.ui.confirmation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.soplant.R
import com.example.soplant.presentation.theme.Grey
import com.example.soplant.presentation.ui.confirmation.components.ConfirmInputComponent
import com.example.soplant.presentation.ui.custom.BaseButton
import com.example.soplant.presentation.ui.custom.LoadingScreenComposable

@Composable
fun ComposeConfirmationScreen(
    navController: NavController
) {
    val focusManager = LocalFocusManager.current

    LoadingScreenComposable(isLoading = false) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(26.dp)
        ) {
            Spacer(modifier = Modifier.height(18.dp))
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
                    modifier = Modifier.padding(20.dp, 0.dp)
                )
            }
            Spacer(modifier = Modifier.height(35.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ConfirmInputComponent(
                    value = "0",
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Right) }
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        focusManager.moveFocus(FocusDirection.Next)
                    })
                ConfirmInputComponent(
                    value = "",
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Right) }
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        focusManager.moveFocus(FocusDirection.Next)
                    })
                ConfirmInputComponent(
                    value = "",
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Right) }
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        focusManager.moveFocus(FocusDirection.Next)
                    })
                ConfirmInputComponent(
                    value = "",
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Right) }
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        focusManager.moveFocus(FocusDirection.Next)
                    })
                ConfirmInputComponent(
                    value = "",
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Right) }
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword,
                        imeAction = ImeAction.Next
                    ),
                    onValueChange = {
                        focusManager.moveFocus(FocusDirection.Next)
                    })
                ConfirmInputComponent(
                    value = "",
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus(true) }
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword,
                        imeAction = ImeAction.Done
                    ),
                    onValueChange = {})
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(text = "Didn't receive? Resend.", style = MaterialTheme.typography.body1, color = MaterialTheme.colors.primary)
            }
            Spacer(modifier = Modifier.height(55.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                BaseButton(
                    text = "Confirm & Finish",
                    enabled = true,
                    modifier = Modifier.fillMaxWidth()
                ) {

                }
            }
        }
    }
}