package com.example.soplant.domain.repositories

import com.example.soplant.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun loginWithCredentials(username: String, password: String): Flow<Resource<Boolean>>
    fun loginWithSSO()
    fun signupUser(email: String, username: String, password: String): Flow<Resource<Boolean>>
}