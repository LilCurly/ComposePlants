package com.example.soplant.commons

object Constants {
    object General {
        const val NETWORK_ERROR: String = "NETWORK_ERROR"
        const val UNEXPECTED_ERROR: String = "UNEXPECTED_ERROR"
    }
    object Amplify {
        const val AMPLIFY_USER_EXISTS: String = "AMPLIFY_USER_EXISTS"
        const val AMPLIFY_USER_WRONG_USER: String = "AMPLIFY_USER_WRONG_USER"
        const val AMPLIFY_UNEXPECTED_ERROR: String = "AMPLIFY_UNEXPECTED_ERROR"
        const val AMPLIFY_USER_NOT_CONFIRMED: String = "AMPLIFY_USER_NOT_CONFIRMED"
        const val AMPLIFY_CODE_EXPIRED: String = "AMPLIFY_CODE_EXPIRED"
        const val AMPLIFY_CODE_MISMATCH: String = "AMPLIFY_CODE_MISMATCH"
        const val AMPLIFY_LIMIT_EXCEEDED: String = "AMPLIFY_LIMIT_EXCEEDED"
    }
    object SharedPreferences {
        const val PREF_USER_ID: String = "PREF_USER_ID"
        const val PREF_USER_USERNAME: String = "PREF_USER_USERNAME"
        const val PREF_USER_EMAIL: String = "PREF_USER_EMAIL"
        const val PREF_USER_NAME: String = "PREF_USER_NAME"
        const val PREF_USER_LOCATION: String = "PREF_USER_LOCATION"
        const val PREF_USER_VERIFIED: String = "PREF_USER_VERIFIED"
        const val PREF_LOGIN_STATUS: String = "PREF_LOGIN_STATUS"
    }
    object LoginStatus {
        const val STATUS_LOGGED_IN: String = "STATUS_LOGGED_IN"
        const val STATUS_LOGGED_OUT: String = "STATUS_LOGGED_OUT"
    }
    object AuthSessionKeys {
        const val USER_ID: String = "sub"
        const val USER_USERNAME: String = "custom:username"
        const val USER_NAME: String = "name"
        const val USER_EMAIL: String = "email"
        const val USER_VERIFIED: String = "custom:is_verified"
        const val USER_LOCATION: String = "custom:location"
    }
}