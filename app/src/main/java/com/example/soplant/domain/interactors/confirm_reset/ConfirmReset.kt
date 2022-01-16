package com.example.soplant.domain.interactors.confirm_reset

import com.example.soplant.domain.repositories.LoginRepository
import javax.inject.Inject

class ConfirmReset @Inject constructor(private val repository: LoginRepository) {
    operator fun invoke(newPassword: String, code: String) = repository.confirmReset(newPassword, code)
}