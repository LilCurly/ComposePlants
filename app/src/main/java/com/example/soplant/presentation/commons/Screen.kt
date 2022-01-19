package com.example.soplant.presentation.commons

import androidx.annotation.StringRes

sealed class Screen(val route: String) {
    sealed class BottomNavigation(val route: String, val name: String) {
        object Wall: BottomNavigation("wall", "Wall")
    }

    object Login: Screen("login")
    object Register: Screen("register")
    object Wall: Screen("wall")
    object ResetPassword: Screen("resetPassword")
    object ConfirmReset: Screen("confirmReset")
    object SocialSignIn: Screen("socialSignIn")
    data class SignUpConfirmation(val userEmail: String = "{userEmail}", val userPassword: String = "{userPassword}"): Screen("confirmation/$userEmail/$userPassword")
}