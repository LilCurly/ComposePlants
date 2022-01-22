package com.example.soplant.application.network.services

import com.example.soplant.commons.Constants
import retrofit2.Response
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface ExplorationService {
    @POST(Constants.Endpoints.ExplorationApi.CREATE_EXPLORATION)
    suspend fun createExploration(@HeaderMap headers: Map<String, String>): Response<Void>
}