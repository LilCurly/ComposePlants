package com.example.soplant.application.repositories

import com.example.soplant.application.network.sources.WalletRemoteDataSource
import com.example.soplant.commons.Constants
import com.example.soplant.domain.entities.Wallet
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
                emit(Resource.error(Constants.Error.WalletApi.FAILED_TO_CREATE, response.error))
            }
        } catch (e: HttpException) {
            emit(Resource.error<Boolean>(Constants.Error.General.UNEXPECTED_ERROR, null))
        } catch (e: IOException) {
            emit(Resource.error<Boolean>(Constants.Error.General.NETWORK_ERROR, null))
        }
    }

    override fun getWallet(): Flow<Resource<Wallet>> = flow {
        try {
            emit(Resource.loading(null))
            val response = remoteDataSource.getWallet()
            if (response.isSuccessful && response.value != null) {
                emit(Resource.success(response.value))
            } else {
                emit(Resource.error(Constants.Error.WalletApi.FAILED_TO_LOAD, response.error))
            }
        } catch (e: HttpException) {
            emit(Resource.error<Wallet>(Constants.Error.General.UNEXPECTED_ERROR, null))
        } catch (e: IOException) {
            emit(Resource.error<Wallet>(Constants.Error.General.NETWORK_ERROR, null))
        }
    }
}