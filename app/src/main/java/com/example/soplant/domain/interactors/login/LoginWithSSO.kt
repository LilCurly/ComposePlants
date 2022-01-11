package com.example.soplant.domain.interactors.login

import com.example.soplant.domain.repositories.LoginRepository
import javax.inject.Inject

class LoginWithSSO @Inject constructor(private val repository: LoginRepository) {
    operator fun invoke() = repository.loginWithSSO()
}