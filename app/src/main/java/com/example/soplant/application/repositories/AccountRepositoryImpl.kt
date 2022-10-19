package com.example.soplant.application.repositories

import com.example.soplant.application.network.sources.AccountRemoteDataSource
import com.example.soplant.commons.Constants
import com.example.soplant.commons.SharedPreferencesManager
import com.example.soplant.domain.entities.User
import com.example.soplant.domain.repositories.AccountRepository
import com.example.soplant.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val remoteDataSource: AccountRemoteDataSource
) : AccountRepository {
    override fun createAccount(): Flow<Resource<User>> = flow {
        try {
            emit(Resource.loading(null))
            val response = remoteDataSource.createAccount()
            if (response.isSuccessful && response.value != null) {
                SharedPreferencesManager.shared().storeLastUserId(response.value.id)
                emit(Resource.success(response.value))
            } else {
                emit(Resource.error(Constants.Error.AccountApi.FAILED_TO_CREATE, response.error))
            }
        } catch (e: HttpException) {
            emit(Resource.error(Constants.Error.General.UNEXPECTED_ERROR, null))
        } catch (e: IOException) {
            emit(Resource.error(Constants.Error.General.NETWORK_ERROR, null))
        }
    }
}