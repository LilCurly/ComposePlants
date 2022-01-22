package com.example.soplant.application.network.utils

import com.amazonaws.mobile.client.AWSMobileClient
import com.amplifyframework.core.Amplify

object Headers {
    fun getDefaultJwtHeader(): Map<String, String> {
        val mobileClient = Amplify.Auth.getPlugin("awsCognitoAuthPlugin").escapeHatch as AWSMobileClient
        val token = mobileClient.tokens.idToken.tokenString

        val header = HashMap<String, String>()
        header["Accept"] = "*/*"
        header["Accept-Encoding"] = "gzip, deflate, br"
        header["Connection"] = "keep-alive"
        header["Authorization"] = token

        return header
    }
}
