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
        object UserApi {
            const val FAILED_TO_GET_USERS: String = "FAILED_TO_GET_USERS"
            const val FAILED_TO_GET_USER: String = "FAILED_TO_GET_USER"
        }
        object WalletApi {
            const val FAILED_TO_CREATE: String = "FAILED_TO_CREATE_WALLET"
            const val FAILED_TO_LOAD: String = "FAILED_TO_LOAD_WALLET"
        }
        object TransactionsApi {
            const val FAILED_TO_CREATE: String = "FAILED_TO_CREATE_WALLET"
            const val FAILED_TO_LOAD: String = "FAILED_TO_LOAD_TRANSACTIONS"
        }
        object ExplorationApi {
            const val FAILED_TO_CREATE: String = "FAILED_TO_CREATE_EXPLORATION"
        }
        object ProductApi {
            const val FAILED_TO_LOAD: String = "FAILED_TO_LOAD_PRODUCTS"
        }
    }
    object ApiError {
        object User {
            const val DOES_NOT_OWN_ANY_USER: String = "ERROR_ACCOUNT_DOES_NOT_OWN_ANY_MANGO_USER"
        }
    }
    object SharedPreferences {
        const val PREF_ACCOUNT_ID: String = "PREF_ACCOUNT_ID"
        const val PREF_USER_USERNAME: String = "PREF_USER_USERNAME"
        const val PREF_ACCOUNT_EMAIL: String = "PREF_ACCOUNT_EMAIL"
        const val PREF_USER_NAME: String = "PREF_USER_NAME"
        const val PREF_USER_LOCATION: String = "PREF_USER_LOCATION"
        const val PREF_USER_VERIFIED: String = "PREF_USER_VERIFIED"
        const val PREF_LOGIN_STATUS: String = "PREF_LOGIN_STATUS"
        const val PREF_SOCIAL_METHOD: String = "PREF_SOCIAL_METHOD"
        const val PREF_PROFILE_URL: String = "PREF_PROFILE_URL"
        const val PREF_LAST_USER_ID: String = "PREF_LAST_USER_ID"
        const val PREF_ACCOUNT_USERS: String = "PREF_ACCOUNT_USERS"
    }
    object LoginStatus {
        const val STATUS_LOGGED_IN: String = "STATUS_LOGGED_IN"
        const val STATUS_LOGGED_OUT: String = "STATUS_LOGGED_OUT"
    }
    object AuthSessionKeys {
        const val ACCOUNT_ID: String = "sub"
        const val USER_USERNAME: String = "custom:username"
        const val LEGAL_TYPE: String = "custom:legal_type"
        const val USER_TYPE: String = "custom:user_type"
        const val LEGAL_NAME: String = "custom:legal_name"
        const val USER_NAME: String = "name"
        const val USER_EMAIL: String = "email"
        const val USER_VERIFIED: String = "custom:is_verified"
        const val USER_LOCATION: String = "custom:location"
        const val USER_LASTNAME: String = "custom:last_name"
        const val USER_FIRSTNAME: String = "custom:first_name"
        const val SOCIAL_IDENTITIES: String = "identities"
        const val USER_PICTURE: String = "picture"
        const val USER_IMAGE_URL: String = "custom:userImageUrl"
        const val ACCOUNT_USERS: String = "custom:users"
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
        object UserApi {
            const val GET_USERS: String = "user"
            const val GET_USER: String = "user/{userId}"
        }
        object ProductApi {
            const val OFFLINE_WALL: String = "{userId}/products/offline"
            const val USER_WALL: String = "{userId}/products"
        }
        object WalletApi {
            const val CREATE_WALLET: String = "wallet"
            const val GET_WALLET: String = "wallet"
            const val GET_TRANSACTIONS: String = "transaction"
        }
        object AccountApi {
            const val CREATE_ACCOUNT: String = "user/first"
        }
        object ExplorationApi {
            const val CREATE_EXPLORATION: String = "/{userId}/exploration"
        }
    }
    object ApiUtils {
        const val PAGINATION_TIMESTAMP: String = "lastPaginationTimestamp"
        const val PAGINATION_KEY: String = "lastEvaluatedKey"
    }
    object Model {
        object Transaction {
            object Status {
                const val FAILURE = "FAILURE"
                const val FROZEN = "FROZEN"
                const val COMPLETED = "COMPLETED"
            }
            object Origin {
                const val BOOST = "BOOST"
                const val BUY_PRODUCT = "BUY_PRODUCT"
                const val SELL_PRODUCT = "SELL_PRODUCT"
                const val FUND_WALLET = "FUND_WALLET"
                const val WITHDRAWAL = "WITHDRAWAL"
            }
        }
    }
}