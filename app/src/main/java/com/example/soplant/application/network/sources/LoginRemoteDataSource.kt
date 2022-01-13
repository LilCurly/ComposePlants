package com.example.soplant.application.network.sources

import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.kotlin.core.Amplify
import javax.inject.Inject

class LoginRemoteDataSource @Inject constructor() {
    suspend fun loginWithCredentials(username: String, password: String): Boolean {
        return try {
            val result = Amplify.Auth.signIn(username, password)
            result.isSignInComplete
        } catch (error: AmplifyException) {
            false
        }
    }

    fun loginWithSSO() {

    }
}