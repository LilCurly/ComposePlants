package com.example.soplant.domain.interactors.confirmation

import com.example.soplant.domain.repositories.AccountRepository
import javax.inject.Inject

class CreateAccount @Inject constructor(private val repository: AccountRepository) {
    operator fun invoke() = repository.createAccount()
}