package com.example.soplant.presentation.utils

import com.example.soplant.commons.Constants

object ErrorCodeConverter {
    fun convertErrorCodeToMessage(errorCode: String): String {
        return when (errorCode) {
            Constants.Amplify.AMPLIFY_USER_EXISTS -> {
                "This email is already registered"
            }
            Constants.Amplify.AMPLIFY_UNEXPECTED_ERROR -> {
                "An unexpected error occurred"
            }
            Constants.General.NETWORK_ERROR -> {
                "You seem to be disconnected. Check your connection and try again."
            }
            Constants.General.UNEXPECTED_ERROR -> {
                "An unexpected error occurred"
            }
            else -> {
                "An unexpected error occurred"
            }
        }
    }
}