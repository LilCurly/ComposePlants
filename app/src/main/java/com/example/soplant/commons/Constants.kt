package com.example.soplant.commons

object Constants {
    object Error {
        object General {
            const val NETWORK_ERROR: String = "NETWORK_ERROR"
            const val UNEXPECTED_ERROR: String = "UNEXPECTED_ERROR"
        }
        object Amplify {
            const val AMPLIFY_USER_EXISTS: String = "AMPLIFY_USER_EXISTS"
            const val AMPLIFY_USER_WRONG_USER: String = "AMPLIFY_USER_WRONG_USER"
            const val AMPLIFY_USER_NOT_FOUND: String = "AMPLIFY_USER_NOT_FOUND"
            const val AMPLIFY_UNEXPECTED_ERROR: String = "AMPLIFY_UNEXPECTED_ERROR"
            const val AMPLIFY_USER_NOT_CONFIRMED: String = "AMPLIFY_USER_NOT_CONFIRMED"
            const val AMPLIFY_CODE_EXPIRED: String = "AMPLIFY_CODE_EXPIRED"
            const val AMPLIFY_CODE_MISMATCH: String = "AMPLIFY_CODE_MISMATCH"
            const val AMPLIFY_LIMIT_EXCEEDED: String = "AMPLIFY_LIMIT_EXCEEDED"
        }
        object AccountApi {
            const val FAILED_TO_CREATE: String = "FAILED_TO_CREATE_ACCOUNT"
        }
        object WalletApi {
            const val FAILED_TO_CREATE: String = "FAILED_TO_CREATE_WALLET"
            const val FAILED_TO_LOAD: String = "FAILED_TO_LOAD_WALLET"
        }
        object ExplorationApi {
            const val FAILED_TO_CREATE: String = "FAILED_TO_CREATE_EXPLORATION"
        }
        object ProductApi {
            const val FAILED_TO_LOAD: String = "FAILED_TO_LOAD_PRODUCTS"
        }
    }
    object SharedPreferences {
        const val PREF_USER_ID: String = "PREF_USER_ID"
        const val PREF_USER_USERNAME: String = "PREF_USER_USERNAME"
        const val PREF_USER_EMAIL: String = "PREF_USER_EMAIL"
        const val PREF_USER_NAME: String = "PREF_USER_NAME"
        const val PREF_USER_LOCATION: String = "PREF_USER_LOCATION"
        const val PREF_USER_VERIFIED: String = "PREF_USER_VERIFIED"
        const val PREF_LOGIN_STATUS: String = "PREF_LOGIN_STATUS"
        const val PREF_SOCIAL_METHOD: String = "PREF_SOCIAL_METHOD"
        const val PREF_PROFILE_URL: String = "PREF_PROFILE_URL"
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
        const val SOCIAL_IDENTITIES: String = "identities"
        const val USER_PICTURE: String = "picture"
        const val USER_IMAGE_URL: String = "custom:userImageUrl"
    }
    object SocialSignInMethod {
        const val GOOGLE: String = "Google"
        const val FACEBOOK: String = "Facebook"
        const val FAILED: String = "FAILED"
    }
    object Endpoints {
        object ResourceApi {
            const val COUNTRIES: String = "countries"
        }
        object ProductApi {
            const val OFFLINE_WALL: String = "list/dwall"
            const val USER_WALL: String = "list/cwall"
        }
        object WalletApi {
            const val CREATE_WALLET: String = "wallet"
            const val GET_WALLET: String = "wallet"
        }
        object AccountApi {
            const val CREATE_ACCOUNT: String = "account"
        }
        object ExplorationApi {
            const val CREATE_EXPLORATION: String = "exploration"
        }
    }
}