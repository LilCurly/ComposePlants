package com.example.soplant.presentation.ui.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.soplant.R
import com.example.soplant.presentation.commons.decorators.HelpScreenDecorator
import com.example.soplant.presentation.commons.decorators.LoadingScreenDecorator
import com.example.soplant.presentation.commons.popup.help.TestHelpPopup
import com.example.soplant.presentation.theme.Grey
import com.example.soplant.presentation.ui.register.components.LegalEntityComponent
import com.example.soplant.presentation.ui.register.components.RegisterAsComponent
import com.example.soplant.presentation.ui.register.components.RegisterFormComponent
import com.example.soplant.redux.register.RegisterScreenState

@Composable
fun ComposeRegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    if (state.formValidated) {
        viewModel.signupUser()
    }

    if (state.signUpSuccessful) {
        viewModel.navigateToConfirmationScreen(navController)
    }

    LoadingScreenDecorator(
        isLoading = state.isSigningUp,
    ) {
        HelpScreenDecorator(
            showHelpButton = state.currentScreenState != RegisterScreenState.FORM,
            popupContent = {
                TestHelpPopup()
            }) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(26.dp)
                    ) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            IconButton(onClick = {
                                if (state.currentScreenState == RegisterScreenState.REGISTER_AS_CHOICE) {
                                    navController.popBackStack()
                                } else {
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
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
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
                        when (state.currentScreenState) {
                            RegisterScreenState.REGISTER_AS_CHOICE -> RegisterAsComponent(viewModel = viewModel)
                            RegisterScreenState.LEGAL_ENTITY_CHOICE -> LegalEntityComponent(
                                viewModel = viewModel
                            )
                            RegisterScreenState.FORM -> {
                                RegisterFormComponent(
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
}