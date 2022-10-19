package com.example.soplant.application.network.sources

import com.example.soplant.application.network.mappers.UserMapper
import com.example.soplant.application.network.services.AccountService
import com.example.soplant.application.network.utils.Headers
import com.example.soplant.application.utils.CustomResponse
import com.example.soplant.domain.entities.User
import javax.inject.Inject

class AccountRemoteDataSource @Inject constructor(
    private val service: AccountService,
    private val userMapper: UserMapper
) {
    suspend fun createAccount(): CustomResponse<User> {
        val headers = Headers.getDefaultJwtHeader()
        val response = service.createAccount(headers)
        if (response.isSuccessful && response.body() != null) {
            return CustomResponse(value = userMapper.mapToEntity(response.body()!!), isSuccessful = response.isSuccessful, code = response.code())
        }
        return CustomResponse(value = null, isSuccessful = false, code = response.code())
    }
}