package com.example.soplant.commons

import androidx.lifecycle.viewModelScope
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobile.client.Callback
import com.amazonaws.mobile.client.results.Tokens
import com.amazonaws.mobileconnectors.cognitoidentityprovider.util.CognitoIdentityProviderClientConfig
import com.example.soplant.presentation.commons.Screen
import com.example.soplant.redux.confirmation.ConfirmationAction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object Token {
    fun refreshToken(coroutineScope: CoroutineScope, onRefreshed: (Boolean) -> Unit, onError: () -> Unit) {
        Thread {
            try {
                CognitoIdentityProviderClientConfig.setRefreshThreshold(1800000)
                AWSMobileClient.getInstance().getTokens(object: Callback<Tokens> {
                    override fun onResult(result: Tokens?) {
                        coroutineScope.launch {
                            UserAttributes.fetchUserAttributes().collect { success ->
                                onRefreshed(success)
                            }
                        }
                    }

                    override fun onError(e: java.lang.Exception?) {
                        onError()
                    }

                })
            } catch (e: Exception) {
                e.printStackTrace()
                onError()
            }
        }.start()
    }
}