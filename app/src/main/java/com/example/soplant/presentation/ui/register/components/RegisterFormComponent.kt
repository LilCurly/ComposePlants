package com.example.soplant.presentation.ui.register.components

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.soplant.presentation.theme.Grey
import com.example.soplant.presentation.theme.RedError
import com.example.soplant.presentation.ui.components.BaseButtonComponent
import com.example.soplant.presentation.ui.custom.*
import com.example.soplant.presentation.ui.register.RegisterViewModel
import com.example.soplant.presentation.utils.ErrorCodeConverter
import com.example.soplant.redux.register.RegisterViewState

@Composable
fun RegisterFormComponent(
    viewModel: RegisterViewModel,
    state: RegisterViewState
) {
    val focusManager = LocalFocusManager.current

    val isLegalEntity = state.selectedRegisterAs == RegisterViewState.RegisterAsOptions.USER_TYPE_LEGAL.optionValue

    val isVisible = remember {
        MutableTransitionState(false)
    }

    LaunchedEffect(Unit) {
        isVisible.targetState = true
    }

    val baseDelay = 25
    val baseDuration = 100
    var mult = 0

    Spacer(modifier = Modifier.height(28.dp))
    CustomAnimations.VisibilityAnimation(
        visibleState = isVisible,
        baseDuration = baseDuration,
        baseDelay = baseDelay,
        mult = mult
    ) {
        CustomDropDown(
            values = state.countries,
            selectedValue = state.selectedCountry,
            title = "Country",
            placeholder = "Enter here",
            isLoading = state.fetchingCountries,
            focusManager = focusManager,
            onSelectChanged = { viewModel.selectCountry(it) }
        )
    }
    mult++
    Spacer(modifier = Modifier.height(9.dp))
    CustomAnimations.VisibilityAnimation(
        visibleState = isVisible,
        baseDuration = baseDuration,
        baseDelay = baseDelay,
        mult = mult
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
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
    }
    mult++
    Spacer(modifier = Modifier.height(9.dp))
    if (isLegalEntity) {
        CustomAnimations.VisibilityAnimation(
            visibleState = isVisible,
            baseDuration = baseDuration,
            baseDelay = baseDelay,
            mult = mult
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                CustomTextField(
                    value = state.legalName,
                    onValueChange = {
                        viewModel.updateLegalName(it)
                    },
                    placeholder = "Enter here",
                    title = "Legal Name",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(9.dp))
        mult++
    }
    CustomAnimations.VisibilityAnimation(
        visibleState = isVisible,
        baseDuration = baseDuration,
        baseDelay = baseDelay,
        mult = mult
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            CustomTextField(
                value = state.firstName,
                onValueChange = {
                    viewModel.updateFirstName(it)
                },
                placeholder = "Enter here",
                title = if (isLegalEntity) "Representative First Name" else "First Name",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )
        }
    }
    mult++
    Spacer(modifier = Modifier.height(9.dp))
    CustomAnimations.VisibilityAnimation(
        visibleState = isVisible,
        baseDuration = baseDuration,
        baseDelay = baseDelay,
        mult = mult
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            CustomTextField(
                value = state.lastName,
                onValueChange = {
                    viewModel.updateLastName(it)
                },
                placeholder = "Enter here",
                title = if (isLegalEntity) "Representative Last Name" else "Last Name",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                )
            )
        }
    }
    mult++
    Spacer(modifier = Modifier.height(9.dp))
    CustomAnimations.VisibilityAnimation(
        visibleState = isVisible,
        baseDuration = baseDuration,
        baseDelay = baseDelay,
        mult = mult
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
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
    }
    mult++
    Spacer(modifier = Modifier.height(6.dp))
    CustomAnimations.VisibilityAnimation(
        visibleState = isVisible,
        baseDuration = baseDuration,
        baseDelay = baseDelay,
        mult = mult
    ) {
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
    }
    mult++
    Spacer(modifier = Modifier.height(16.dp))
    CustomAnimations.VisibilityAnimation(
        visibleState = isVisible,
        baseDuration = baseDuration,
        baseDelay = baseDelay,
        mult = mult
    ) {
        CustomCheckbox(
            modifier = Modifier
                .fillMaxWidth()
                .padding(11.dp, 0.dp),
            text = "I agree with terms of service and privacy policy.",
            isChecked = state.tosChecked,
            onSelect = { viewModel.updateTosChecked(!state.tosChecked) }
        )
    }
    mult++
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
        CustomAnimations.VisibilityAnimation(
            visibleState = isVisible,
            baseDuration = baseDuration,
            baseDelay = baseDelay,
            mult = mult
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                BaseButtonComponent(
                    text = "Signup",
                    enabled = state.canSignUp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    viewModel.validateForm()
                }
            }
        }
    }
    KeyboardSpacer()
}