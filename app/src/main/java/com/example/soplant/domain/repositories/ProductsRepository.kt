package com.example.soplant.domain.repositories

import com.example.soplant.domain.entities.ProductList
import com.example.soplant.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    fun getOfflineWall(lastPaginationTimestamp: Long): Flow<Resource<ProductList>>
}