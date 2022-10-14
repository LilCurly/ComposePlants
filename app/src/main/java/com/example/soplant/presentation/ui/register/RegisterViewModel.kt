package com.example.soplant.presentation.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.soplant.presentation.commons.Screen
import com.example.soplant.presentation.ui.custom.CustomDropDownModel
import com.example.soplant.redux.Store
import com.example.soplant.redux.register.RegisterAction
import com.example.soplant.redux.register.RegisterViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val store: Store<RegisterViewState, RegisterAction>) :
    ViewModel() {
    val state: StateFlow<RegisterViewState> = store.state

    init {
        fetchCountries()
    }

    private fun fetchCountries() {
        val action = RegisterAction.FetchCountries

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun signupUser() {
        val action = RegisterAction.ProcessSignup

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun validateForm() {
        val action = RegisterAction.SignupClicked

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateEmail(newEmail: String) {
        val action = RegisterAction.UpdatingEmail(newEmail)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateLegalName(newLegalName: String) {
        val action = RegisterAction.UpdatingLegalName(newLegalName)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateFirstName(newFirstName: String) {
        val action = RegisterAction.UpdatingFirstName(newFirstName)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateLastName(newLastName: String) {
        val action = RegisterAction.UpdatingLastName(newLastName)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updatePassword(newPassword: String) {
        val action = RegisterAction.UpdatingPassword(newPassword)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun updateTosChecked(newStatus: Boolean) {
        val action = RegisterAction.ClickedReadTos(newStatus)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun navigateToConfirmationScreen(navController: NavController) {
        val action = RegisterAction.NavigateToConfirmationScreen

        viewModelScope.launch {
            store.dispatch(action, this)
        }

        navController.navigate(
            Screen.SignUpConfirmation(
                userEmail = state.value.email,
                userPassword = state.value.password
            ).route
        ) {
            popUpTo(Screen.Login.route)
        }
    }

    fun selectCountry(selectedCountry: CustomDropDownModel?) {
        val action = RegisterAction.SelectingCountry(selectedCountry)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun selectRegisterAs(selectedRegisterAs: String) {
        val action = RegisterAction.SelectingRegisterAs(selectedRegisterAs)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun selectLegalEntity(selectedLegalEntity: String) {
        val action = RegisterAction.SelectingLegalEntity(selectedLegalEntity)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun navigateBack() {
        val action = RegisterAction.NavigatingBack

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }
}