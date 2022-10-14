package com.example.soplant.application.network.utils

import com.amazonaws.mobile.client.AWSMobileClient
import com.amplifyframework.core.Amplify

object User {
    fun getUserId(): String {
        val mobileClient = Amplify.Auth.getPlugin("awsCognitoAuthPlugin").escapeHatch as AWSMobileClient
        return mobileClient.userSub
    }
}