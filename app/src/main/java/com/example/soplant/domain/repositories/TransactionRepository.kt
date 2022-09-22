package com.example.soplant.domain.repositories

import com.example.soplant.domain.entities.TransactionList
import com.example.soplant.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun getTransactions(lastEvaluatedKey: String?): Flow<Resource<TransactionList>>
}