package com.example.soplant.presentation.ui.register

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.soplant.R
import com.example.soplant.presentation.commons.Screen
import com.example.soplant.presentation.theme.Grey
import com.example.soplant.presentation.theme.GreyLight
import com.example.soplant.presentation.theme.RedError
import com.example.soplant.presentation.ui.custom.*
import com.example.soplant.presentation.ui.extensions.noRippleClickable
import com.example.soplant.presentation.utils.ErrorCodeConverter

@Composable
fun ComposeRegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val focusManager = LocalFocusManager.current

    if (state.formValidated) {
        viewModel.signupUser()
    }

    if (state.signUpSuccessful) {
        viewModel.navigateToConfirmationScreen(navController)
    }

    LoadingScreenComposable(isLoading = state.isSigningUp) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
        ) {
            item {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(26.dp)) {
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
                            text = "Welcome :)",
                            color = Grey,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Please enter your details",
                            color = Grey,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = Modifier.height(28.dp))
                    CustomDropDown(
                        values = state.countries,
                        selectedValue = state.selectedCountry,
                        title = "Country",
                        placeholder = "Enter here",
                        isLoading = state.fetchingCountries,
                        focusManager = focusManager,
                        onSelectChanged = { viewModel.selectCountry(it) }
                    )
                    Spacer(modifier = Modifier.height(9.dp))
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
                            keyboardActions = KeyboardActions(
                                onNext = { focusManager.moveFocus(FocusDirection.Down) }
                            ),
                            errorMessage = if (state.isEmailValid) "" else "Invalid email format"
                        )
                    }
                    Spacer(modifier = Modifier.height(9.dp))
                    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                        CustomTextField(
                            value = state.username,
                            onValueChange = {
                                viewModel.updateUsername(it)
                            },
                            placeholder = "Enter here",
                            title = "Username",
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
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
                            value = state.password,
                            onValueChange = { viewModel.updatePassword(it) },
                            placeholder = "Enter here",
                            title = "Password",
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(onDone = {
                                focusManager.clearFocus(true)
                            }),
                            hideContent = true
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(11.dp, 0.dp)) {
                        Text(
                            text = "Password should contain at least 8 characters, with at least one uppercase character, one lowercase character, one number and one special character.",
                            style = MaterialTheme.typography.caption,
                            color = if (state.isPasswordValid) Grey else RedError
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    CustomCheckbox(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(11.dp, 0.dp),
                        text = "I agree with terms of service and privacy policy.",
                        isChecked = state.tosChecked,
                        onSelect = { viewModel.updateTosChecked(!state.tosChecked) }
                    )
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
                            BaseButton(
                                text = "Signup",
                                enabled = state.canSignUp,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                viewModel.validateForm()
                            }
                        }
                    }
                }
            }
        }
    }
}