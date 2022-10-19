package com.example.soplant.application.repositories

import com.example.soplant.application.network.sources.UserRemoteDataSource
import com.example.soplant.commons.Constants
import com.example.soplant.domain.entities.User
import com.example.soplant.domain.repositories.UserRepository
import com.example.soplant.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource
) : UserRepository {
    override fun getUsers(): Flow<Resource<List<User>>> = flow {
        try {
            emit(Resource.loading(null))
            val response = remoteDataSource.getUsers()
            if (response.isSuccessful) {
                emit(Resource.success(response.value))
            } else {
                emit(Resource.error(Constants.Error.UserApi.FAILED_TO_GET_USERS, response.error))
            }
        } catch (e: HttpException) {
            emit(Resource.error(Constants.Error.General.UNEXPECTED_ERROR, null))
        } catch (e: IOException) {
            emit(Resource.error(Constants.Error.General.NETWORK_ERROR, null))
        }
    }

    override fun getUser(userId: String): Flow<Resource<User>> = flow {
        try {
            emit(Resource.loading(null))
            val response = remoteDataSource.getUser(userId)
            if (response.isSuccessful) {
                emit(Resource.success(response.value))
            } else {
                emit(Resource.error(Constants.Error.UserApi.FAILED_TO_GET_USER, response.error))
            }
        } catch (e: HttpException) {
            emit(Resource.error(Constants.Error.General.UNEXPECTED_ERROR, null))
        } catch (e: IOException) {
            emit(Resource.error(Constants.Error.General.NETWORK_ERROR, null))
        }
    }
}