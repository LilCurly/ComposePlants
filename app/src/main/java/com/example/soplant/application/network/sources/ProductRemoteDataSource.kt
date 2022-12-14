package com.example.soplant.application.network.sources

import com.example.soplant.application.network.mappers.ProductListMapper
import com.example.soplant.application.network.services.ProductService
import com.example.soplant.application.network.utils.Headers
import com.example.soplant.application.utils.CustomResponse
import com.example.soplant.commons.SharedPreferencesManager
import com.example.soplant.domain.entities.ProductList
import javax.inject.Inject

class ProductRemoteDataSource @Inject constructor(
    private val productService: ProductService,
    private val productListMapper: ProductListMapper
) {
    suspend fun getOfflineWall(): ProductList {
        return productListMapper.mapToEntity(productService.getOfflineWall())
    }

    suspend fun getUserWall(lastPaginationTimestamp: Long?): CustomResponse<ProductList> {
        val headers = Headers.getDefaultJwtHeader()
        val response = productService.getUserWall(headers, SharedPreferencesManager.shared().getLastUserId(), lastPaginationTimestamp)
        var result: ProductList? = null
        if (response.isSuccessful && response.body() != null) {
            result = productListMapper.mapToEntity(response.body()!!)
        }
        return CustomResponse(result, response.isSuccessful, response.code())
    }
}