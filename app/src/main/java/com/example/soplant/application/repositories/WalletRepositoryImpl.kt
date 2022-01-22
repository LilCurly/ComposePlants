package com.example.soplant.application.repositories

import com.example.soplant.application.network.sources.WalletRemoteDataSource
import com.example.soplant.commons.Constants
import com.example.soplant.domain.repositories.WalletRepository
import com.example.soplant.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class WalletRepositoryImpl @Inject constructor(
    private val remoteDataSource: WalletRemoteDataSource
): WalletRepository {
    override fun createWallet(): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.loading(null))
            val response = remoteDataSource.createWallet()
            if (response.isSuccessful) {
                emit(Resource.success(true))
            } else {
                emit(Resource.error(Constants.Error.WalletApi.FAILED_TO_CREATE))
            }
        } catch (e: HttpException) {
            emit(Resource.error<Boolean>(Constants.Error.General.UNEXPECTED_ERROR))
        } catch (e: IOException) {
            emit(Resource.error<Boolean>(Constants.Error.General.NETWORK_ERROR))
        }
    }
}