package com.example.soplant.domain.interactors.confirmation

import com.example.soplant.domain.repositories.LoginRepository
import javax.inject.Inject

class ResendCode @Inject constructor(private val repository: LoginRepository) {
    operator fun invoke(email: String) = repository.resendCode(email)
}