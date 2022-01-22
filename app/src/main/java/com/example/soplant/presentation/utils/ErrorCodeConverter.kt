package com.example.soplant.presentation.utils

import com.example.soplant.commons.Constants

object ErrorCodeConverter {
    fun convertErrorCodeToMessage(errorCode: String): String {
        return when (errorCode) {
            Constants.Error.Amplify.AMPLIFY_USER_EXISTS -> {
                "This email is already registered"
            }
            Constants.Error.Amplify.AMPLIFY_UNEXPECTED_ERROR -> {
                "An unexpected error occurred"
            }
            Constants.Error.Amplify.AMPLIFY_CODE_EXPIRED -> {
                "This code has expired. Try generating a new one."
            }
            Constants.Error.Amplify.AMPLIFY_CODE_MISMATCH -> {
                "Wrong code"
            }
            Constants.Error.Amplify.AMPLIFY_LIMIT_EXCEEDED -> {
                "Attempt limit exceeded. Try again later."
            }
            Constants.Error.Amplify.AMPLIFY_USER_WRONG_USER -> {
                "The email or password you provided is incorrect"
            }
            Constants.Error.General.NETWORK_ERROR -> {
                "You seem to be disconnected. Check your connection and try again."
            }
            Constants.Error.General.UNEXPECTED_ERROR -> {
                "An unexpected error occurred"
            }
            Constants.Error.AccountApi.FAILED_TO_CREATE, Constants.Error.ExplorationApi.FAILED_TO_CREATE, Constants.Error.WalletApi.FAILED_TO_CREATE -> {
                "Failed to create account. Please retry."
            }
            else -> {
                "An unexpected error occurred"
            }
        }
    }
}