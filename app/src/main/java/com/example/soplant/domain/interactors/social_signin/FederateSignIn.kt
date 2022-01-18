package com.example.soplant.domain.interactors.social_signin

import com.example.soplant.domain.repositories.LoginRepository
import javax.inject.Inject

class FederateSignIn @Inject constructor(private val repository: LoginRepository) {
    operator fun invoke(username: String, location: String) = repository.federateSignIn(username, location)
}