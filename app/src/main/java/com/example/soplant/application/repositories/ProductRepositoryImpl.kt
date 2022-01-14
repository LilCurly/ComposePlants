package com.example.soplant.application.repositories

import com.example.soplant.application.network.sources.ProductRemoteDataSource
import com.example.soplant.domain.entities.ProductList
import com.example.soplant.domain.repositories.ProductsRepository
import com.example.soplant.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProductRemoteDataSource
): ProductsRepository {
    override fun getOfflineWall(lastPaginationTimestamp: Long): Flow<Resource<ProductList>> = flow {
        try {
            emit(Resource.loading())
            val result = remoteDataSource.getOfflineWall()
            emit(Resource.success(result))
        } catch (e: HttpException) {
            emit(Resource.error(""))
        } catch (e: IOException) {
            emit(Resource.error(""))
        }
    }
}