package com.example.soplant.presentation.commons

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.soplant.R

sealed class Screen(val route: String) {
    sealed class BottomNavigation(val route: String, val iconSelected: @Composable () -> Unit, val iconUnselected: @Composable () -> Unit) {
        object Wall: BottomNavigation("wall", iconSelected = {
            Image(painter = painterResource(id = R.drawable.icon_home), contentDescription = null)
        }, iconUnselected = {
            Image(painter = painterResource(id = R.drawable.icon_home_light), contentDescription = null)
        })
        object Research: BottomNavigation("research", iconSelected = {
            Image(painter = painterResource(id = R.drawable.icon_search), contentDescription = null)
        }, iconUnselected = {
            Image(painter = painterResource(id = R.drawable.icon_search_light), contentDescription = null)
        })
        object Chat: BottomNavigation("chat", iconSelected = {
            Image(painter = painterResource(id = R.drawable.icon_chat), contentDescription = null)
        }, iconUnselected = {
            Image(painter = painterResource(id = R.drawable.icon_chat_light), contentDescription = null)
        })
        object Profile: BottomNavigation("profile", iconSelected = {
            Image(painter = painterResource(id = R.drawable.icon_user), contentDescription = null)
        }, iconUnselected = {
            Image(painter = painterResource(id = R.drawable.icon_user_light), contentDescription = null)
        })
    }

    object Login: Screen("login")
    object Register: Screen("register")
    object Wall: Screen("wall")
    object Research: Screen("research")
    object Chat: Screen("chat")
    object Profile: Screen("profile")
    object ResetPassword: Screen("resetPassword")
    object ConfirmReset: Screen("confirmReset")
    object SocialSignIn: Screen("socialSignIn")
    data class SignUpConfirmation(val userEmail: String = "{userEmail}", val userPassword: String = "{userPassword}"): Screen("confirmation/$userEmail/$userPassword")
}