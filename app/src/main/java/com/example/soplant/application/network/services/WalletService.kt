package com.example.soplant.application.network.services

import com.example.soplant.application.network.models.WalletDto
import com.example.soplant.commons.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface WalletService {
    @POST(Constants.Endpoints.WalletApi.CREATE_WALLET)
    suspend fun createWallet(@HeaderMap headers: Map<String, String>): Response<Void>
    @GET(Constants.Endpoints.WalletApi.GET_WALLET)
    suspend fun getWallet(@HeaderMap headers: Map<String, String>): Response<WalletDto>
}