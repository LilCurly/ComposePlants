package com.example.soplant.domain.interactors.confirmation

import com.example.soplant.domain.repositories.WalletRepository
import javax.inject.Inject

class CreateWallet @Inject constructor(private val repository: WalletRepository) {
    operator fun invoke() = repository.createWallet()
}