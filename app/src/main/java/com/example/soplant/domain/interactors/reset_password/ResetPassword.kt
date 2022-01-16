package com.example.soplant.domain.interactors.reset_password

import com.example.soplant.domain.repositories.LoginRepository
import javax.inject.Inject

class ResetPassword @Inject constructor(private val repository: LoginRepository) {
    operator fun invoke(email: String) = repository.resetPassword(email)
}