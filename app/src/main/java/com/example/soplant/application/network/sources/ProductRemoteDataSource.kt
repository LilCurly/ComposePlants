package com.example.soplant.application.network.sources

import com.example.soplant.application.network.mappers.ProductListMapper
import com.example.soplant.application.network.services.ProductService
import com.example.soplant.domain.entities.ProductList
import javax.inject.Inject

class ProductRemoteDataSource @Inject constructor(
    private val productService: ProductService,
    private val productListMapper: ProductListMapper
) {
    suspend fun getOfflineWall(): ProductList {
        return productListMapper.mapToEntity(productService.getOfflineWall())
    }
}