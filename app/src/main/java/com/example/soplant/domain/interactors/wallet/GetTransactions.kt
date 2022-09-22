package com.example.soplant.domain.interactors.wallet

import com.example.soplant.domain.repositories.TransactionRepository
import javax.inject.Inject

class GetTransactions @Inject constructor(private val repository: TransactionRepository) {
    operator fun invoke(lastEvaluatedKey: String?) = repository.getTransactions(lastEvaluatedKey)
}