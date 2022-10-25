package com.example.soplant.presentation.ui.social_signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.soplant.R
import com.example.soplant.commons.SharedPreferencesManager
import com.example.soplant.presentation.commons.decorators.HelpScreenDecorator
import com.example.soplant.presentation.commons.decorators.LoadingScreenDecorator
import com.example.soplant.presentation.commons.popup.help.TestHelpPopupContent
import com.example.soplant.presentation.theme.Grey
import com.example.soplant.presentation.ui.social_signin.components.ComposeSocialProfileCardComponent
import com.example.soplant.presentation.ui.social_signin.components.LegalEntityComponent
import com.example.soplant.presentation.ui.social_signin.components.RegisterAsComponent
import com.example.soplant.presentation.ui.social_signin.components.RegisterFormComponent
import com.example.soplant.redux.social_signin.SocialSignInViewState

@Composable
fun ComposeSocialSignInScreen(
    navController: NavController,
    viewModel: SocialSignInViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val userSocialName: String = SharedPreferencesManager.shared().getUserName()
    val userImageUrl: String = SharedPreferencesManager.shared().getPictureUrl()

    LaunchedEffect(true) {
        if (userSocialName.isNotEmpty()) {
            val splitUserName = userSocialName.split(" ").toMutableList()
            if (splitUserName.isNotEmpty()) {
                viewModel.updateFirstName(splitUserName[0])
                splitUserName.removeAt(0)
            }
            if (splitUserName.isNotEmpty()) {
                viewModel.updateLastName(splitUserName.joinToString(" "))
            }
        }

        if (userImageUrl.isNotEmpty()) {
            viewModel.updateUserImageUrl(userImageUrl)
        }
    }

    if (state.updateSuccessful) {
        viewModel.navigateToWall(navController)
    }

    if (state.signOutSuccessful) {
        viewModel.navigateToLogin(navController)
    }

    LoadingScreenDecorator(isLoading = state.isLoading) {
        HelpScreenDecorator(
            showHelpButton = state.screenState != SocialSignInViewState.SocialSignInScreenState.FORM,
            popupContent = {
                TestHelpPopupContent()
            }) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(26.dp)
                    ) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            IconButton(modifier = Modifier.alpha(if (state.screenState == SocialSignInViewState.SocialSignInScreenState.REGISTER_AS_CHOICE) 0f else 1f), onClick = {
                                if (state.screenState != SocialSignInViewState.SocialSignInScreenState.REGISTER_AS_CHOICE) {
                                    viewModel.navigateBack()
                                }
                            }) {
                                Image(
                                    painter = painterResource(id = R.drawable.back_arrow),
                                    contentDescription = null
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = null,
                                modifier = Modifier.size(111.dp, 59.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            ComposeSocialProfileCardComponent(
                                userName = userSocialName,
                                socialMethod = SharedPreferencesManager.shared().getSocialMethod(),
                                image = rememberImagePainter(
                                    SharedPreferencesManager.shared().getPictureUrl()
                                )
                            ) {
                                viewModel.signOut()
                            }
                        }

                        Spacer(modifier = Modifier.height(26.dp))
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Welcome :)",
                                style = MaterialTheme.typography.h1,
                                color = MaterialTheme.colors.primary,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "To continue please enter your remaining details.",
                                style = MaterialTheme.typography.body1,
                                color = Grey,
                                textAlign = TextAlign.Center
                            )
                        }
                        when (state.screenState) {
                            SocialSignInViewState.SocialSignInScreenState.REGISTER_AS_CHOICE -> RegisterAsComponent(
                                viewModel = viewModel
                            )
                            SocialSignInViewState.SocialSignInScreenState.LEGAL_ENTITY_CHOICE -> LegalEntityComponent(
                                viewModel = viewModel
                            )
                            SocialSignInViewState.SocialSignInScreenState.FORM -> RegisterFormComponent(
                                viewModel = viewModel,
                                state = state
                            )
                        }
                    }
                }
            }
        }
    }
}