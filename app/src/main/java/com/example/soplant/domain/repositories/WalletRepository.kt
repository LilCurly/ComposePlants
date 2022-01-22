package com.example.soplant.domain.repositories

import com.example.soplant.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface WalletRepository {
    fun createWallet(): Flow<Resource<Boolean>>
}