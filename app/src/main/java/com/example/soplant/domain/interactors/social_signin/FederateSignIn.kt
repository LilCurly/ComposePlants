package com.example.soplant.domain.interactors.social_signin

import com.example.soplant.domain.repositories.LoginRepository
import javax.inject.Inject

class FederateSignIn @Inject constructor(private val repository: LoginRepository) {
    operator fun invoke(legalName: String, firstName: String, lastName: String, registerAs: String, legalType: String, location: String, userImageUrl: String) = repository.federateSignIn(legalName, firstName, lastName, registerAs, legalType, location, userImageUrl)
}