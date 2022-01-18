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
import com.example.soplant.commons.SharedPreferencesManager
import javax.inject.Inject

class LoginRemoteDataSource @Inject constructor() {
    suspend fun loginWithCredentials(username: String, password: String): AmplifyModel {
        return try {
            val result = Amplify.Auth.signIn(username, password)
            Log.i("LoginDataSource", "Successfully logged in user")
            AmplifyModel(result.isSignInComplete, null)
        } catch (error: AuthException.UserNotConfirmedException) {
            Log.e("LoginDataSource", "Failed to login user", error)
            AmplifyModel(false, Constants.Amplify.AMPLIFY_USER_NOT_CONFIRMED)
        } catch (error: AuthException.NotAuthorizedException) {
            Log.e("LoginDataSource", "Failed to login user", error)
            AmplifyModel(false, Constants.Amplify.AMPLIFY_USER_WRONG_USER)
        } catch (error: AuthException.InvalidParameterException) {
            Log.e("LoginDataSource", "Failed to login user", error)
            AmplifyModel(false, Constants.Amplify.AMPLIFY_USER_WRONG_USER)
        } catch (error: AmplifyException) {
            Log.e("LoginDataSource", "Failed to login user", error)
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
            Log.i("LoginDataSource", "Successfully sign up user")
            AmplifyModel(result.isSignUpComplete, null)
        } catch (error: AuthException.UsernameExistsException) {
            Log.e("LoginDataSource", "Failed to sign up user", error)
            AmplifyModel(false, Constants.Amplify.AMPLIFY_USER_EXISTS)
        } catch (error: AmplifyException) {
            Log.e("LoginDataSource", "Failed to sign up user", error)
            AmplifyModel(false, Constants.Amplify.AMPLIFY_UNEXPECTED_ERROR)
        }
    }

    suspend fun validateUser(email: String, code: String): AmplifyModel {
        return try {
            val result = Amplify.Auth.confirmSignUp(username = email, confirmationCode = code)
            Log.i("LoginDataSource", "Successfully validated user")
            AmplifyModel(result.isSignUpComplete, null)
        } catch (error: AuthException.CodeExpiredException) {
            Log.e("LoginDataSource", "Failed to validate user", error)
            AmplifyModel(false, Constants.Amplify.AMPLIFY_CODE_EXPIRED)
        } catch (error: AuthException.CodeMismatchException) {
            Log.e("LoginDataSource", "Failed to validate user", error)
            AmplifyModel(false, Constants.Amplify.AMPLIFY_CODE_MISMATCH)
        } catch (error: AuthException.LimitExceededException) {
            Log.e("LoginDataSource", "Failed to validate user", error)
            AmplifyModel(false, Constants.Amplify.AMPLIFY_LIMIT_EXCEEDED)
        } catch (error: AmplifyException) {
            Log.e("LoginDataSource", "Failed to validate user", error)
            AmplifyModel(false, Constants.Amplify.AMPLIFY_UNEXPECTED_ERROR)
        }
    }

    suspend fun resendCode(email: String): AmplifyModel {
        return try {
            Amplify.Auth.resendSignUpCode(username = email)
            Log.i("LoginDataSource", "Successfully resent sign up code")
            AmplifyModel(true, null)
        } catch (error: AmplifyException) {
            Log.e("LoginDataSource", "Failed to resend sign up code", error)
            AmplifyModel(false, Constants.Amplify.AMPLIFY_UNEXPECTED_ERROR)
        }
    }

    suspend fun resetPassword(email: String): AmplifyModel {
        return try {
            Amplify.Auth.resetPassword(username = email)
            Log.i("LoginDataSource", "Reset password first step successful")
            AmplifyModel(true, null)
        } catch (error: AuthException.UserNotFoundException) {
            Log.e("LoginDataSource", "Reset password first step failed", error)
            AmplifyModel(false, Constants.Amplify.AMPLIFY_USER_NOT_FOUND)
        } catch (error: AmplifyException) {
            Log.e("LoginDataSource", "Reset password first step failed", error)
            AmplifyModel(false, Constants.Amplify.AMPLIFY_UNEXPECTED_ERROR)
        }
    }

    suspend fun confirmReset(newPassword: String, code: String): AmplifyModel {
        return try {
            Amplify.Auth.confirmResetPassword(newPassword, code)
            Log.i("LoginDataSource", "Successfully confirmed password reset")
            AmplifyModel(true, null)
        } catch (error: AuthException.CodeExpiredException) {
            Log.e("LoginDataSource", "Failed to confirm reset password", error)
            AmplifyModel(false, Constants.Amplify.AMPLIFY_CODE_EXPIRED)
        } catch (error: AuthException.CodeMismatchException) {
            Log.e("LoginDataSource", "Failed to confirm reset password", error)
            AmplifyModel(false, Constants.Amplify.AMPLIFY_CODE_MISMATCH)
        } catch (error: AuthException.LimitExceededException) {
            Log.e("LoginDataSource", "Failed to confirm reset password", error)
            AmplifyModel(false, Constants.Amplify.AMPLIFY_LIMIT_EXCEEDED)
        } catch (error: AmplifyException) {
            Log.e("LoginDataSource", "Failed to confirm reset password", error)
            AmplifyModel(false, Constants.Amplify.AMPLIFY_UNEXPECTED_ERROR)
        }
    }

    suspend fun federateSignIn(username: String, location: String): AmplifyModel {
        val attrs = listOf(
            AuthUserAttribute(AuthUserAttributeKey.custom(Constants.AuthSessionKeys.USER_USERNAME), username),
            AuthUserAttribute(AuthUserAttributeKey.custom(Constants.AuthSessionKeys.USER_LOCATION), location)
        )
        return try {
            Amplify.Auth.updateUserAttributes(attrs)
            Log.i("LoginDataSource", "Successfully updated firstSocialLogin attribute")
            AmplifyModel(true, null)
        } catch (error: AmplifyException) {
            Log.e("LoginDataSource", "Federate sign in failed", error)
            AmplifyModel(false, Constants.Amplify.AMPLIFY_UNEXPECTED_ERROR)
        }
    }

    suspend fun signOut(): AmplifyModel {
        return try {
            Amplify.Auth.signOut()
            SharedPreferencesManager.shared().signOut()
            Log.i("LoginDataSource", "Successfully signed out")
            AmplifyModel(true, null)
        } catch (error: AmplifyException) {
            Log.e("LoginDataSource", "Sign out failed", error)
            AmplifyModel(false, Constants.Amplify.AMPLIFY_UNEXPECTED_ERROR)
        }
    }
}