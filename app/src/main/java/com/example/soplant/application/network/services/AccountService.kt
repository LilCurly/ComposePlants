package com.example.soplant.application.network.services

import com.example.soplant.application.network.models.UserDto
import com.example.soplant.commons.Constants
import retrofit2.Response
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface AccountService {
    @POST(Constants.Endpoints.AccountApi.CREATE_ACCOUNT)
    suspend fun createAccount(@HeaderMap headers: Map<String, String>): Response<UserDto>
}