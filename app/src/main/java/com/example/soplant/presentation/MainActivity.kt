package com.example.soplant.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.amplifyframework.AmplifyException
import com.amplifyframework.auth.AuthProvider.facebook
import com.amplifyframework.auth.AuthProvider.google
import com.amplifyframework.kotlin.core.Amplify
import com.example.soplant.R
import com.example.soplant.commons.Constants
import com.example.soplant.commons.SharedPreferencesManager
import com.example.soplant.commons.UserAttributes
import com.example.soplant.presentation.commons.Screen
import com.example.soplant.presentation.theme.Black
import com.example.soplant.presentation.theme.SoPlantTheme
import com.example.soplant.presentation.theme.White
import com.example.soplant.presentation.ui.add_fund.ComposeAddFundScreen
import com.example.soplant.presentation.ui.chat.ComposeChatScreen
import com.example.soplant.presentation.ui.confirm_reset.ComposeConfirmResetScreen
import com.example.soplant.presentation.ui.confirmation.ComposeConfirmationScreen
import com.example.soplant.presentation.ui.create_post.ComposeCreatePostScreen
import com.example.soplant.presentation.ui.custom.CustomBackground
import com.example.soplant.presentation.ui.custom.variantGreenButtonColors
import com.example.soplant.presentation.ui.custom.zeroButtonElevation
import com.example.soplant.presentation.ui.extensions.advancedShadow
import com.example.soplant.presentation.ui.login.ComposeLoginScreen
import com.example.soplant.presentation.ui.profile.ComposeProfileScreen
import com.example.soplant.presentation.ui.register.ComposeRegisterScreen
import com.example.soplant.presentation.ui.research.ComposeResearchScreen
import com.example.soplant.presentation.ui.reset_password.ComposeResetPasswordScreen
import com.example.soplant.presentation.ui.social_signin.ComposeSocialSignInScreen
import com.example.soplant.presentation.ui.wall.ComposeWallScreen
import com.example.soplant.presentation.ui.wallet.ComposeWalletScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val _federateSignIn = MutableStateFlow("")
    val federatedSignIn: StateFlow<String> = _federateSignIn

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SoPlantTheme {
                val transitionTime = 300
                val navController = rememberAnimatedNavController()
                val navControllerState by navController.currentBackStackEntryAsState()
                val shouldShowBottomBar =
                    navControllerState?.destination?.route == Screen.Wall.route ||
                            navControllerState?.destination?.route == Screen.Research.route ||
                            navControllerState?.destination?.route == Screen.Chat.route ||
                            navControllerState?.destination?.route == Screen.Profile.route
                Scaffold(
                    bottomBar = {
                        if (shouldShowBottomBar) {
                            ComposeBottomNavigation(navController = navController)
                        }
                    }
                ) {
                    CustomBackground(
                        painter = painterResource(id = R.drawable.background),
                        contentDescription = null,
                        shouldShowBottomBar = shouldShowBottomBar
                    ) {
                        val isSignedIn = SharedPreferencesManager.shared().isLoggedIn()
                        val users = SharedPreferencesManager.shared().getAccountUsers()
                        AnimatedNavHost(
                            navController = navController,
                            startDestination = if (!isSignedIn) Screen.Login.route else if (users.isEmpty()) Screen.SocialSignIn.route else Screen.Wall.route
                        ) {
                            composable(Screen.Login.route, enterTransition = {
                                slideIntoContainer(
                                    AnimatedContentScope.SlideDirection.Right,
                                    animationSpec = tween(transitionTime)
                                )
                            }, exitTransition = {
                                slideOutOfContainer(
                                    AnimatedContentScope.SlideDirection.Left,
                                    animationSpec = tween(transitionTime)
                                )
                            }) {
                                ComposeLoginScreen(navController = navController)
                            }
                            composable(Screen.Register.route, enterTransition = {
                                slideIntoContainer(
                                    AnimatedContentScope.SlideDirection.Left,
                                    animationSpec = tween(transitionTime)
                                )
                            }, exitTransition = {
                                slideOutOfContainer(
                                    AnimatedContentScope.SlideDirection.Right,
                                    animationSpec = tween(transitionTime)
                                )
                            }) {
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
                            composable(Screen.Research.route) {
                                ComposeResearchScreen(
                                    navController = navController
                                )
                            }
                            composable(Screen.Chat.route) {
                                ComposeChatScreen(
                                    navController = navController
                                )
                            }
                            composable(Screen.Profile.route) {
                                ComposeProfileScreen(
                                    navController = navController
                                )
                            }
                            composable(Screen.CreatePost.route) {
                                ComposeCreatePostScreen(
                                    navController = navController
                                )
                            }
                            composable(Screen.Wallet.route) {
                                ComposeWalletScreen(
                                    navController = navController
                                )
                            }
                            composable(Screen.AddFund.route) {
                                ComposeAddFundScreen(
                                    navController = navController
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun ComposeBottomNavigation(
        navController: NavController
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(101.dp)
        ) {
            BottomNavigation(
                backgroundColor = White,
                modifier = Modifier
                    .height(69.dp)
                    .fillMaxWidth()
                    .advancedShadow(
                        color = Black,
                        alpha = 0.1f,
                        cornersRadius = 26.dp,
                        shadowBlurRadius = 20.dp
                    )
                    .clip(RoundedCornerShape(26.dp, 26.dp, 0.dp, 0.dp))
                    .align(Alignment.BottomCenter)
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                Spacer(modifier = Modifier.width(20.dp))
                listOf(
                    Screen.BottomNavigation.Wall,
                    Screen.BottomNavigation.Research,
                    Screen.BottomNavigation.Chat,
                    Screen.BottomNavigation.Profile
                ).forEachIndexed { i, item ->
                    val isSelected =
                        currentDestination?.hierarchy?.any { it.route == item.route } == true
                    BottomNavigationItem(
                        selected = isSelected,
                        icon = if (isSelected) item.iconSelected else item.iconUnselected,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(0) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        })
                    if (i == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
                Spacer(modifier = Modifier.width(20.dp))
            }
            Button(
                shape = CircleShape,
                colors = variantGreenButtonColors(),
                elevation = zeroButtonElevation(),
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.TopCenter)
                    .advancedShadow(
                        color = MaterialTheme.colors.primary,
                        alpha = 0.28f,
                        cornersRadius = 32.dp,
                        shadowBlurRadius = 20.dp,
                        offsetY = 4.dp
                    ),
                onClick = {
                    navController.navigate(Screen.CreatePost.route) {
                        launchSingleTop = true
                    }
                }) {
                Image(
                    painter = painterResource(id = R.drawable.icon_add_white),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
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