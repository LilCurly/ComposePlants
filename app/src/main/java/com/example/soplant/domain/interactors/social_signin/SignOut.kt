package com.example.soplant.domain.interactors.social_signin

import com.example.soplant.domain.repositories.LoginRepository
import javax.inject.Inject

class SignOut @Inject constructor(private val repository: LoginRepository) {
    operator fun invoke() = repository.signOut()
}