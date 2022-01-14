package com.example.soplant.application.repositories

import com.example.soplant.application.network.sources.LoginRemoteDataSource
import com.example.soplant.commons.Constants
import com.example.soplant.domain.repositories.LoginRepository
import com.example.soplant.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val remoteSource: LoginRemoteDataSource
): LoginRepository {
    override fun loginWithCredentials(username: String, password: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.loading(null))
            val result = remoteSource.loginWithCredentials(username, password)
            emit(Resource.success(result.operationSuccess))
        } catch (e: HttpException) {
            emit(Resource.error<Boolean>(Constants.General.UNEXPECTED_ERROR))
        } catch (e: IOException) {
            emit(Resource.error<Boolean>(Constants.General.NETWORK_ERROR))
        }
    }

    override fun loginWithSSO() {
        TODO("Not yet implemented")
    }

    override fun signupUser(
        email: String,
        username: String,
        password: String
    ): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.loading(null))
            val result = remoteSource.signupUser(email, username, password)
            if (result.operationSuccess) {
                emit(Resource.success(result.operationSuccess))
            } else {
                emit(Resource.error<Boolean>(result.errorCode ?: Constants.Amplify.AMPLIFY_UNEXPECTED_ERROR))
            }
        } catch (e: HttpException) {
            emit(Resource.error<Boolean>(Constants.General.UNEXPECTED_ERROR))
        } catch (e: IOException) {
            emit(Resource.error<Boolean>(Constants.General.NETWORK_ERROR))
        }
    }

    override fun validateUser(email: String, code: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.loading(null))
            val result = remoteSource.validateUser(email, code)
            if (result.operationSuccess) {
                emit(Resource.success(result.operationSuccess))
            } else {
                emit(Resource.error<Boolean>(result.errorCode ?: Constants.Amplify.AMPLIFY_UNEXPECTED_ERROR))
            }
        } catch (e: HttpException) {
            emit(Resource.error<Boolean>(Constants.General.UNEXPECTED_ERROR))
        } catch (e: IOException) {
            emit(Resource.error<Boolean>(Constants.General.NETWORK_ERROR))
        }
    }

    override fun resendCode(email: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.loading(null))
            val result = remoteSource.resendCode(email)
            if (result.operationSuccess) {
                emit(Resource.success(result.operationSuccess))
            } else {
                emit(Resource.error<Boolean>(result.errorCode ?: Constants.Amplify.AMPLIFY_UNEXPECTED_ERROR))
            }
        } catch (e: HttpException) {
            emit(Resource.error<Boolean>(Constants.General.UNEXPECTED_ERROR))
        } catch (e: IOException) {
            emit(Resource.error<Boolean>(Constants.General.NETWORK_ERROR))
        }
    }
}