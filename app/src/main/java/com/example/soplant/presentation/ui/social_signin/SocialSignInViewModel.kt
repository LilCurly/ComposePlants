package com.example.soplant.presentation.ui.social_signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobile.client.Callback
import com.amazonaws.mobile.client.results.Tokens
import com.amazonaws.mobileconnectors.cognitoidentityprovider.util.CognitoIdentityProviderClientConfig
import com.example.soplant.commons.UserAttributes
import com.example.soplant.presentation.commons.Screen
import com.example.soplant.presentation.ui.custom.CustomDropDownModel
import com.example.soplant.redux.Store
import com.example.soplant.redux.social_signin.SocialSignInAction
import com.example.soplant.redux.social_signin.SocialSignInViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SocialSignInViewModel @Inject constructor(private val store: Store<SocialSignInViewState, SocialSignInAction>) :
    ViewModel() {
    val state: StateFlow<SocialSignInViewState> = store.state

    init {
        val action = SocialSignInAction.FetchCountries

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateFirstName(firstName: String) {
        val action = SocialSignInAction.UpdatingFirstName(firstName)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateLastName(lastName: String) {
        val action = SocialSignInAction.UpdatingLastName(lastName)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateLegalName(legalName: String) {
        val action = SocialSignInAction.UpdatingLegalName(legalName)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateRegisterAs(registerAs: String) {
        val action = SocialSignInAction.SelectingRegisterAs(registerAs)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateLegalEntity(legalEntity: String) {
        val action = SocialSignInAction.SelectingLegalEntity(legalEntity)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun navigateBack() {
        val action = SocialSignInAction.NavigateBack

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateUserImageUrl(userImageUrl: String) {
        val action = SocialSignInAction.UpdatingUserImageUrl(userImageUrl)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateTosState() {
        val action = SocialSignInAction.ClickingTos

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun clickContinue() {
        val action = SocialSignInAction.ClickingContinue

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun navigateToWall(navController: NavController) {
        val action = SocialSignInAction.NavigateToWall

        viewModelScope.launch {
            UserAttributes.fetchUserAttributes().collect { success ->
                store.dispatch(action, this)
            }
        }

        navController.navigate(Screen.Wall.route) {
            popUpTo(0) {
                inclusive = true
            }
        }
    }

    fun signOut() {
        val action = SocialSignInAction.ClickingChange

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun navigateToLogin(navController: NavController) {
        val action = SocialSignInAction.NavigateToLogin

        viewModelScope.launch {
            store.dispatch(action, this)
        }

        navController.navigate(Screen.Login.route) {
            popUpTo(0) {
                inclusive = true
            }
        }
    }

    fun selectCountry(newSelectedCountry: CustomDropDownModel?) {
        val action = SocialSignInAction.SelectingCountry(newSelectedCountry)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }
}