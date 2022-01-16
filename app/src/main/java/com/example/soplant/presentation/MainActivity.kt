package com.example.soplant.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.example.soplant.R
import com.example.soplant.commons.SharedPreferencesManager
import com.example.soplant.presentation.theme.SoPlantTheme
import com.example.soplant.presentation.ui.confirm_reset.ComposeConfirmResetScreen
import com.example.soplant.presentation.ui.confirmation.ComposeConfirmationScreen
import com.example.soplant.presentation.ui.custom.CustomBackground
import com.example.soplant.presentation.ui.login.ComposeLoginScreen
import com.example.soplant.presentation.ui.register.ComposeRegisterScreen
import com.example.soplant.presentation.ui.reset_password.ComposeResetPasswordScreen
import com.example.soplant.presentation.ui.wall.ComposeWallScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SoPlantTheme {
                CustomBackground(painter = painterResource(id = R.drawable.background), contentDescription = null) {
                    val navController = rememberNavController()
                    val isSignedIn = SharedPreferencesManager.shared().isLoggedIn()
                    NavHost(navController = navController, startDestination = if (isSignedIn) "wall" else "login") {
                        composable("login") {
                            ComposeLoginScreen(navController = navController)
                        }
                        composable("register") {
                            ComposeRegisterScreen(navController = navController)
                        }
                        composable("confirmation/{userEmail}/{userPassword}") {
                            ComposeConfirmationScreen(
                                navController = navController,
                                userEmail = it.arguments?.getString("userEmail") ?: "",
                                userPassword = it.arguments?.getString("userPassword") ?: ""
                            )
                        }
                        composable("wall") {
                            ComposeWallScreen(navController = navController)
                        }
                        composable("resetPassword") {
                            ComposeResetPasswordScreen(navController = navController)
                        }
                        composable("confirmReset") {
                            ComposeConfirmResetScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}