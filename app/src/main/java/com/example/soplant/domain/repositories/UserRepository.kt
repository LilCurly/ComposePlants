package com.example.soplant.domain.repositories

import com.example.soplant.domain.entities.User
import com.example.soplant.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<Resource<List<User>>>
    fun getUser(userId: String): Flow<Resource<User>>
}