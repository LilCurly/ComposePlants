package com.example.soplant.application.network.sources

import com.example.soplant.application.network.services.ExplorationService
import com.example.soplant.application.network.utils.Headers
import com.example.soplant.application.utils.CustomResponse
import com.example.soplant.commons.SharedPreferencesManager
import javax.inject.Inject

class ExplorationRemoteDataSource @Inject constructor(
    private val service: ExplorationService
) {
    suspend fun createExploration(): CustomResponse<Void> {
        val headers = Headers.getDefaultJwtHeader()
        val response = service.createExploration(headers, SharedPreferencesManager.shared().getLastUserId())
        return CustomResponse(
            isSuccessful = response.isSuccessful,
            code = response.code(),
            error = null
        )
    }
}