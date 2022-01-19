package com.example.soplant.application.network.services

import com.example.soplant.application.network.models.ProductListDto
import com.example.soplant.commons.Constants
import retrofit2.http.GET

interface ProductService {
    @GET(Constants.Endpoints.ProductApi.OFFLINE_WALL)
    suspend fun getOfflineWall(): ProductListDto
}