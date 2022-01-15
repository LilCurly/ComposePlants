package com.example.soplant.commons

import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.kotlin.core.Amplify
import com.example.soplant.redux.confirmation.ConfirmationAction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object UserAttributes {
    suspend fun fetchUserAttributes(): Flow<Boolean> = flow {
        try {
            val result = Amplify.Auth.fetchUserAttributes()
            Log.i("SoPlantApplication", "Successfully fetched user attributes")
            result.forEach { attr ->
                SharedPreferencesManager.shared().storeLoggedIn()
                when (attr.key.keyString) {
                    Constants.AuthSessionKeys.USER_ID -> {
                        SharedPreferencesManager.shared().storeUserId(attr.value)
                    }
                    Constants.AuthSessionKeys.USER_EMAIL -> {
                        SharedPreferencesManager.shared().storeUserEmail(attr.value)
                    }
                    Constants.AuthSessionKeys.USER_LOCATION -> {
                        SharedPreferencesManager.shared().storeUserLocation(attr.value)
                    }
                    Constants.AuthSessionKeys.USER_NAME -> {
                        SharedPreferencesManager.shared().storeUserName(attr.value)
                    }
                    Constants.AuthSessionKeys.USER_USERNAME -> {
                        SharedPreferencesManager.shared().storeUserUsername(attr.value)
                    }
                    Constants.AuthSessionKeys.USER_VERIFIED -> {
                        SharedPreferencesManager.shared().storeUserVerified(attr.value.equals("true"))
                    }
                }
            }
            emit(true)
        } catch (error: AmplifyException) {
            Log.i("SoPlantApplication", "Failed to fetch auth session")
            emit(false)
        }
    }
}