package com.example.soplant.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amazonaws.mobile.auth.core.signin.AuthException
import com.amplifyframework.AmplifyException
import com.amplifyframework.api.ApiException
import com.amplifyframework.auth.AuthProvider.facebook
import com.amplifyframework.auth.AuthProvider.google
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.kotlin.core.Amplify
import com.example.soplant.R
import com.example.soplant.SoPlantApplication
import com.example.soplant.commons.Constants
import com.example.soplant.commons.SharedPreferencesManager
import com.example.soplant.commons.UserAttributes
import com.example.soplant.presentation.commons.Screen
import com.example.soplant.presentation.theme.SoPlantTheme
import com.example.soplant.presentation.ui.confirm_reset.ComposeConfirmResetScreen
import com.example.soplant.presentation.ui.confirmation.ComposeConfirmationScreen
import com.example.soplant.presentation.ui.custom.CustomBackground
import com.example.soplant.presentation.ui.login.ComposeLoginScreen
import com.example.soplant.presentation.ui.register.ComposeRegisterScreen
import com.example.soplant.presentation.ui.reset_password.ComposeResetPasswordScreen
import com.example.soplant.presentation.ui.social_signin.ComposeSocialSignInScreen
import com.example.soplant.presentation.ui.wall.ComposeWallScreen
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import java.security.AuthProvider

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val _federateSignIn = MutableStateFlow("")
    val federatedSignIn: StateFlow<String> = _federateSignIn

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SoPlantTheme {
                CustomBackground(painter = painterResource(id = R.drawable.background), contentDescription = null) {
                    val navController = rememberNavController()
                    val isSignedIn = SharedPreferencesManager.shared().isLoggedIn()
                    val username = SharedPreferencesManager.shared().getUserUsername()
                    val location = SharedPreferencesManager.shared().getUserLocation()
                    NavHost(navController = navController, startDestination = if (!isSignedIn) Screen.Login.route else if (isSignedIn && (username.isEmpty() || location.isEmpty())) Screen.SocialSignIn.route else Screen.Wall.route) {
                        composable(Screen.Login.route) {
                            ComposeLoginScreen(navController = navController)
                        }
                        composable(Screen.Register.route) {
                            ComposeRegisterScreen(navController = navController)
                        }
                        composable(Screen.SignUpConfirmation().route) {
                            ComposeConfirmationScreen(
                                navController = navController,
                                userEmail = it.arguments?.getString("userEmail") ?: "",
                                userPassword = it.arguments?.getString("userPassword") ?: ""
                            )
                        }
                        composable(Screen.Wall.route) {
                            ComposeWallScreen(navController = navController)
                        }
                        composable(Screen.ResetPassword.route) {
                            ComposeResetPasswordScreen(navController = navController)
                        }
                        composable(Screen.ConfirmReset.route) {
                            ComposeConfirmResetScreen(navController = navController)
                        }
                        composable(Screen.SocialSignIn.route) {
                            ComposeSocialSignInScreen(
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }

    suspend fun federateSignInGoogle(scope: CoroutineScope) {
        try {
            val result = Amplify.Auth.signInWithSocialWebUI(google(), this)
            Log.i("AuthSession", "Google sign in OK: $result")
            UserAttributes.fetchUserAttributes().collect { success ->
                if (success) {
                    _federateSignIn.value = Constants.SocialSignInMethod.GOOGLE
                } else {
                    _federateSignIn.value = Constants.SocialSignInMethod.FAILED
                }
            }
        } catch (error: AmplifyException) {
            Log.e("AuthSession", "Google sign in failed", error)
            _federateSignIn.value = Constants.SocialSignInMethod.FAILED
        }
    }

    suspend fun federateSignInFacebook(scope: CoroutineScope) {
        try {
            val result = Amplify.Auth.signInWithSocialWebUI(facebook(), this)
            Log.i("AuthSession", "Facebook sign in OK: $result")
            UserAttributes.fetchUserAttributes().collect { success ->
                if (success) {
                    _federateSignIn.value = Constants.SocialSignInMethod.FACEBOOK
                } else {
                    _federateSignIn.value = Constants.SocialSignInMethod.FAILED
                }
            }
        } catch (error: AmplifyException) {
            Log.e("AuthSession", "Facebook sign in failed", error)
            _federateSignIn.value = Constants.SocialSignInMethod.FAILED
        }
    }

    fun clearState() {
        _federateSignIn.value = ""
    }
}