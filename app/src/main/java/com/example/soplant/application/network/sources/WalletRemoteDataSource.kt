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
        val response = service.createWallet(Headers.getDefaultJwtHeader())
        return CustomResponse(isSuccessful = response.isSuccessful, code = response.code())
    }

    suspend fun getWallet(): CustomResponse<Wallet> {
        val response = service.getWallet(Headers.getDefaultJwtHeader())
        var wallet: Wallet? = null
        if (response.isSuccessful && response.body() != null) {
            wallet = mapper.mapToEntity(response.body()!!)
        }
        return CustomResponse(wallet, response.isSuccessful, response.code())
    }
}