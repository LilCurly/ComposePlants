package com.example.soplant.application.network.sources

import com.example.soplant.application.network.models.request.CreateAccountRequest
import com.example.soplant.application.network.services.AccountService
import com.example.soplant.application.network.utils.Headers
import com.example.soplant.application.utils.CustomResponse
import javax.inject.Inject

class AccountRemoteDataSource @Inject constructor(
    private val service: AccountService
) {
    suspend fun createAccount(): CustomResponse<Void> {
        val response = service.createAccount(Headers.getDefaultJwtHeader())
        return CustomResponse(isSuccessful = response.isSuccessful, code = response.code())
    }
}