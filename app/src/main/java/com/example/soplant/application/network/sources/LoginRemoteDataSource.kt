package com.example.soplant.application.network.sources

import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.auth.AuthException
import com.amplifyframework.auth.AuthUserAttribute
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.kotlin.core.Amplify
import com.example.soplant.application.network.models.AmplifyModel
import com.example.soplant.commons.Constants
import javax.inject.Inject

class LoginRemoteDataSource @Inject constructor() {
    suspend fun loginWithCredentials(username: String, password: String): AmplifyModel {
        return try {
            val result = Amplify.Auth.signIn(username, password)
            AmplifyModel(result.isSignInComplete, null)
        } catch (error: AmplifyException) {
            AmplifyModel(false, Constants.Amplify.AMPLIFY_UNEXPECTED_ERROR)
        }
    }

    suspend fun signupUser(email: String, username: String, password: String): AmplifyModel {
        val options = AuthSignUpOptions.builder()
            .userAttributes(mutableListOf(
                AuthUserAttribute(AuthUserAttributeKey.email(), email),
                AuthUserAttribute(AuthUserAttributeKey.name(), username),
                AuthUserAttribute(AuthUserAttributeKey.custom("custom:username"), username),
            ))
            .build()
        return try {
            val result = Amplify.Auth.signUp(username = email, password = password, options = options)
            AmplifyModel(result.isSignUpComplete, null)
        } catch (error: AuthException.UsernameExistsException) {
            AmplifyModel(false, Constants.Amplify.AMPLIFY_USER_EXISTS)
        } catch (error: AmplifyException) {
            AmplifyModel(false, Constants.Amplify.AMPLIFY_UNEXPECTED_ERROR)
        }
    }

    fun loginWithSSO() {

    }
}