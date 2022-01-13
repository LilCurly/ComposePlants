package com.example.soplant.application.network.services

import com.example.soplant.application.network.models.ProductListDto
import retrofit2.http.GET

interface ProductService {
    @GET("/list/dwall")
    suspend fun getOfflineWall(): ProductListDto
}