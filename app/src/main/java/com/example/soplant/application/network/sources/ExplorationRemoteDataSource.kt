package com.example.soplant.application.network.sources

import com.example.soplant.application.network.services.ExplorationService
import com.example.soplant.application.network.utils.Headers
import com.example.soplant.application.network.utils.User
import com.example.soplant.application.utils.CustomResponse
import javax.inject.Inject

class ExplorationRemoteDataSource @Inject constructor(
    private val service: ExplorationService
) {
    suspend fun createExploration(): CustomResponse<Void> {
        val response = service.createExploration(Headers.getDefaultJwtHeader(), User.getUserId())
        return CustomResponse(isSuccessful = response.isSuccessful, code = response.code())
    }
}