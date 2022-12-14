package com.example.soplant.domain.repositories

import com.example.soplant.domain.entities.User
import com.example.soplant.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun createAccount(): Flow<Resource<User>>
}