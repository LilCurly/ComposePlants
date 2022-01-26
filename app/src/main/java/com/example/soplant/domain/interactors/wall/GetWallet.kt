package com.example.soplant.domain.interactors.wall

import com.example.soplant.domain.repositories.WalletRepository
import javax.inject.Inject

class GetWallet @Inject constructor(private val repository: WalletRepository) {
    operator fun invoke() = repository.getWallet()
}