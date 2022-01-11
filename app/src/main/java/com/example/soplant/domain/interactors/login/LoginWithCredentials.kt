package com.example.soplant.domain.interactors.login

import com.example.soplant.domain.repositories.LoginRepository
import javax.inject.Inject

class LoginWithCredentials @Inject constructor(private val repository: LoginRepository) {
    operator fun invoke(username: String, password: String) = repository.loginWithCredentials(username, password)
}