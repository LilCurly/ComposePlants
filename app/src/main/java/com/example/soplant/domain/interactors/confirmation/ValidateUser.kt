package com.example.soplant.domain.interactors.confirmation

import com.example.soplant.domain.repositories.LoginRepository
import javax.inject.Inject

class ValidateUser @Inject constructor(private val repository: LoginRepository) {
    operator fun invoke(email: String, code: String) = repository.validateUser(email, code)
}