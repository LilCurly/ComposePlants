package com.example.soplant.application.network.utils

import android.util.Log
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobile.client.Callback
import com.amazonaws.mobile.client.results.Tokens
import com.amplifyframework.core.Amplify
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object Headers {
    suspend fun getDefaultJwtHeader() = suspendCoroutine { continuation ->
        val mobileClient = Amplify.Auth.getPlugin("awsCognitoAuthPlugin").escapeHatch as AWSMobileClient
        mobileClient.getTokens(object: Callback<Tokens> {
            override fun onResult(result: Tokens?) {
                result?.let {
                    val header = HashMap<String, String>()
                    header["Accept"] = "*/*"
                    header["Accept-Encoding"] = "gzip, deflate, br"
                    header["Connection"] = "keep-alive"
                    header["Authorization"] = it.idToken.tokenString

                    continuation.resume(header)
                }
            }

            override fun onError(e: Exception?) {
                Log.e("Token", e?.message, e)

                continuation.resumeWithException(e!!)
            }
        })

    }
}
