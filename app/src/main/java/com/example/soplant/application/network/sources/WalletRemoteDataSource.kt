package com.example.soplant.application.network.sources

import com.example.soplant.application.network.services.WalletService
import com.example.soplant.application.network.utils.Headers
import com.example.soplant.application.utils.CustomResponse
import javax.inject.Inject

class WalletRemoteDataSource @Inject constructor(
    private val service: WalletService
) {
    suspend fun createWallet(): CustomResponse<Void> {
        val response = service.createWallet(Headers.getDefaultJwtHeader())
        return CustomResponse(isSuccessful = response.isSuccessful, code = response.code())
    }
}