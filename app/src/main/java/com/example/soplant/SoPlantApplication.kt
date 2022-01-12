package com.example.soplant

import android.app.Application
import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.Amplify
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SoPlantApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        try {
            Amplify.addPlugin(AWSCognitoAuthPlugin())
            Amplify.configure(applicationContext)
            Log.i("SoPlantApplication", "Initialized Amplifys")
        } catch (error: AmplifyException) {
            Log.e("SoPlantApplication", "Could not initialize Amplify", error)
        }
    }
}