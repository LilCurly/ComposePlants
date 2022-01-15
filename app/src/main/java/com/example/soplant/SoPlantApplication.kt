package com.example.soplant

import android.app.Application
import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.auth.AuthChannelEventName
import com.amplifyframework.auth.AuthException
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession
import com.amplifyframework.auth.result.AuthSessionResult
import com.amplifyframework.kotlin.core.Amplify
import com.amplifyframework.core.InitializationStatus
import com.amplifyframework.hub.HubChannel
import com.amplifyframework.hub.HubSubscriber
import com.example.soplant.commons.Constants
import com.example.soplant.commons.SharedPreferencesManager
import com.example.soplant.commons.UserAttributes
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn

@HiltAndroidApp
class SoPlantApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        SharedPreferencesManager.initializeSharedPreferencesManager(applicationContext)

        initializeAmplify()
        initializeAuthListener()
        checkAuthStatus()
    }

    private fun initializeAmplify() {
        try {
            Amplify.addPlugin(AWSCognitoAuthPlugin())
            Amplify.configure(applicationContext)
            Log.i("SoPlantApplication", "Initialized Amplifys")
        } catch (error: AmplifyException) {
            Log.e("SoPlantApplication", "Could not initialize Amplify", error)
        }
    }

    private fun initializeAuthListener() {
        MainScope().launch {
            Amplify.Hub.subscribe(HubChannel.AUTH).collect {
                when (it.name) {
                    InitializationStatus.SUCCEEDED.toString() ->
                        Log.i("SoPlantApplication", "Auth successfully initialized")
                    InitializationStatus.FAILED.toString() ->
                        Log.i("SoPlantApplication", "Auth failed to succeed")
                    else -> when (AuthChannelEventName.valueOf(it.name)) {
                        AuthChannelEventName.SIGNED_IN -> {
                            Log.i("SoPlantApplication", "Auth just became signed in.")
                        }
                        AuthChannelEventName.SIGNED_OUT -> {
                            Log.i("SoPlantApplication", "Auth just became signed out.")
                            SharedPreferencesManager.shared().signOut()
                        }
                        AuthChannelEventName.SESSION_EXPIRED -> {
                            Log.i("SoPlantApplication", "Auth session just expired.")
                            SharedPreferencesManager.shared().signOut()
                        }
                        else ->
                            Log.w("SoPlantApplication", "Unhandled Auth Event: ${it.name}")
                    }
                }
            }
        }
    }

    private fun checkAuthStatus() {
        MainScope().launch {
            try {
                val session = Amplify.Auth.fetchAuthSession() as AWSCognitoAuthSession
                if (session.isSignedIn) {
                    Log.i("SoPlantApplication", "signed in")
                    UserAttributes.fetchUserAttributes().launchIn(this)
                } else {
                    Log.i("SoPlantApplication", "signed out")
                    SharedPreferencesManager.shared().signOut()
                }
            } catch (error: AuthException) {
                Log.e("SoPlantApplication", "Failed to fetch session", error)
            }
        }
    }
}