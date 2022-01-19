package com.example.soplant.domain.interactors.register

import com.example.soplant.domain.repositories.LoginRepository
import javax.inject.Inject

class SignupUser @Inject constructor(private val repository: LoginRepository) {
    operator fun invoke(email: String, username: String, password: String, location: String) = repository.signupUser(email, username, password, location)
}