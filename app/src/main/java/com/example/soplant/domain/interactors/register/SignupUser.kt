package com.example.soplant.domain.interactors.register

import com.example.soplant.domain.repositories.LoginRepository
import javax.inject.Inject

class SignupUser @Inject constructor(private val repository: LoginRepository) {
    operator fun invoke(registerAs: String, legalType: String, email: String, legalName: String, firstName: String, lastName: String, password: String, location: String) = repository.signupUser(registerAs, legalType, email, legalName, firstName, lastName, password, location)
}