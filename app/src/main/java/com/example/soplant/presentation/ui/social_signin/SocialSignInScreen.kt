package com.example.soplant.presentation.ui.social_signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.soplant.R
import com.example.soplant.commons.SharedPreferencesManager
import com.example.soplant.presentation.theme.Grey
import com.example.soplant.presentation.theme.RedError
import com.example.soplant.presentation.theme.SilverGrey
import com.example.soplant.presentation.ui.custom.*
import com.example.soplant.presentation.ui.extensions.advancedShadow
import com.example.soplant.presentation.ui.social_signin.components.ComposeSocialProfileCardComponent
import com.example.soplant.presentation.utils.ErrorCodeConverter

@Composable
fun ComposeSocialSignInScreen(
    navController: NavController,
    viewModel: SocialSignInViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current
    val state by viewModel.state.collectAsState()
    val userSocialName: String = SharedPreferencesManager.shared().getUserName()
    val userImageUrl: String = SharedPreferencesManager.shared().getPictureUrl()

    if (state.username.isEmpty() && userSocialName.isNotEmpty()) {
        viewModel.updateUsername(userSocialName)
    }

    if (state.userImageUrl.isEmpty() && userImageUrl.isNotEmpty()) {
        viewModel.updateUserImageUrl(userImageUrl)
    }

    if (state.updateSuccessful) {
        viewModel.navigateToWall(navController)
    }

    if (state.signOutSuccessful) {
        viewModel.navigateToLogin(navController)
    }

    LoadingScreenComposable(isLoading = state.isLoading) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(26.dp)) {
                    Spacer(modifier = Modifier.height(18.dp))
                    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = null,
                            modifier = Modifier.size(111.dp, 59.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(51.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        ComposeSocialProfileCardComponent(
                            userName = userSocialName,
                            socialMethod = SharedPreferencesManager.shared().getSocialMethod(),
                            image = rememberImagePainter(SharedPreferencesManager.shared().getPictureUrl())
                        ) {
                            viewModel.signOut()
                        }
                    }
                    Spacer(modifier = Modifier.height(26.dp))
                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Welcome :)",
                            style = MaterialTheme.typography.h1,
                            color = MaterialTheme.colors.primary,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "To continue please define a username and the country you live in.",
                            style = MaterialTheme.typography.body1,
                            color = Grey,
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = Modifier.height(27.dp))
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
                    Row(modifier = Modifier.fillMaxWidth()) {
                        CustomTextField(
                            value = state.username,
                            onValueChange = {
                                viewModel.updateUsername(it)
                            },
                            placeholder = "Enter here",
                            title = "Username",
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = { focusManager.clearFocus(true) }
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    CustomCheckbox(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(11.dp, 0.dp),
                        text = "I agree with terms of service and privacy policy.",
                        isChecked = state.tosChecked,
                        onSelect = { viewModel.updateTosState() }
                    )
                    Spacer(modifier = Modifier.height(38.dp))
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
                                text = "Continue",
                                enabled = state.canContinue,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                viewModel.clickContinue()
                            }
                        }
                    }
                }
            }
        }
    }
}