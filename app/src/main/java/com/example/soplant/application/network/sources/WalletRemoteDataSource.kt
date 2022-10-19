package com.example.soplant.application.network.sources

import com.example.soplant.application.network.mappers.WalletMapper
import com.example.soplant.application.network.services.WalletService
import com.example.soplant.application.network.utils.Headers
import com.example.soplant.application.utils.CustomResponse
import com.example.soplant.domain.entities.Wallet
import javax.inject.Inject

class WalletRemoteDataSource @Inject constructor(
    private val service: WalletService,
    private val mapper: WalletMapper
) {
    suspend fun createWallet(): CustomResponse<Void> {
        val headers = Headers.getDefaultJwtHeader()
        val response = service.createWallet(headers)
        return CustomResponse(isSuccessful = response.isSuccessful, code = response.code())
    }

    suspend fun getWallet(): CustomResponse<Wallet> {
        val headers = Headers.getDefaultJwtHeader()
        val response = service.getWallet(headers)
        var wallet: Wallet? = null
        if (response.isSuccessful && response.body() != null) {
            wallet = mapper.mapToEntity(response.body()!!)
        }
        return CustomResponse(wallet, response.isSuccessful, response.code())
    }
}