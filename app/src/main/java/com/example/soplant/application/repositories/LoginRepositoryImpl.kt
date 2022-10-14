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
            if (result.operationSuccess) {
                emit(Resource.success(result.operationSuccess))
            } else {
                emit(Resource.error<Boolean>(result.errorCode ?: Constants.Error.Amplify.AMPLIFY_UNEXPECTED_ERROR))
            }
        } catch (e: HttpException) {
            emit(Resource.error<Boolean>(Constants.Error.General.UNEXPECTED_ERROR))
        } catch (e: IOException) {
            emit(Resource.error<Boolean>(Constants.Error.General.NETWORK_ERROR))
        }
    }

    override fun loginWithSSO() {
        TODO("Not yet implemented")
    }

    override fun signupUser(
        registerAs: String,
        legalType: String,
        email: String,
        legalName: String,
        firstName: String,
        lastName: String,
        password: String,
        location: String
    ): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.loading(null))
            val result = remoteSource.signupUser(registerAs, legalType, legalName, email, firstName, lastName, location, password)
            if (result.operationSuccess) {
                emit(Resource.success(result.operationSuccess))
            } else {
                emit(Resource.error<Boolean>(result.errorCode ?: Constants.Error.Amplify.AMPLIFY_UNEXPECTED_ERROR))
            }
        } catch (e: HttpException) {
            emit(Resource.error<Boolean>(Constants.Error.General.UNEXPECTED_ERROR))
        } catch (e: IOException) {
            emit(Resource.error<Boolean>(Constants.Error.General.NETWORK_ERROR))
        }
    }

    override fun validateUser(email: String, code: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.loading(null))
            val result = remoteSource.validateUser(email, code)
            if (result.operationSuccess) {
                emit(Resource.success(result.operationSuccess))
            } else {
                emit(Resource.error<Boolean>(result.errorCode ?: Constants.Error.Amplify.AMPLIFY_UNEXPECTED_ERROR))
            }
        } catch (e: HttpException) {
            emit(Resource.error<Boolean>(Constants.Error.General.UNEXPECTED_ERROR))
        } catch (e: IOException) {
            emit(Resource.error<Boolean>(Constants.Error.General.NETWORK_ERROR))
        }
    }

    override fun resendCode(email: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.loading(null))
            val result = remoteSource.resendCode(email)
            if (result.operationSuccess) {
                emit(Resource.success(result.operationSuccess))
            } else {
                emit(Resource.error<Boolean>(result.errorCode ?: Constants.Error.Amplify.AMPLIFY_UNEXPECTED_ERROR))
            }
        } catch (e: HttpException) {
            emit(Resource.error<Boolean>(Constants.Error.General.UNEXPECTED_ERROR))
        } catch (e: IOException) {
            emit(Resource.error<Boolean>(Constants.Error.General.NETWORK_ERROR))
        }
    }

    override fun resetPassword(email: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.loading(null))
            val result = remoteSource.resetPassword(email)
            if (result.operationSuccess) {
                emit(Resource.success(result.operationSuccess))
            } else {
                emit(Resource.error<Boolean>(result.errorCode ?: Constants.Error.Amplify.AMPLIFY_UNEXPECTED_ERROR))
            }
        } catch (e: HttpException) {
            emit(Resource.error<Boolean>(Constants.Error.General.UNEXPECTED_ERROR))
        } catch (e: IOException) {
            emit(Resource.error<Boolean>(Constants.Error.General.NETWORK_ERROR))
        }
    }

    override fun confirmReset(newPassword: String, code: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.loading(null))
            val result = remoteSource.confirmReset(newPassword, code)
            if (result.operationSuccess) {
                emit(Resource.success(result.operationSuccess))
            } else {
                emit(Resource.error<Boolean>(result.errorCode ?: Constants.Error.Amplify.AMPLIFY_UNEXPECTED_ERROR))
            }
        } catch (e: HttpException) {
            emit(Resource.error<Boolean>(Constants.Error.General.UNEXPECTED_ERROR))
        } catch (e: IOException) {
            emit(Resource.error<Boolean>(Constants.Error.General.NETWORK_ERROR))
        }
    }

    override fun federateSignIn(username: String, location: String, userImageUrl: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.loading(null))
            val result = remoteSource.federateSignIn(username, location, userImageUrl)
            if (result.operationSuccess) {
                emit(Resource.success(result.operationSuccess))
            } else {
                emit(Resource.error<Boolean>(result.errorCode ?: Constants.Error.Amplify.AMPLIFY_UNEXPECTED_ERROR))
            }
        } catch (e: HttpException) {
            emit(Resource.error<Boolean>(Constants.Error.General.UNEXPECTED_ERROR))
        } catch (e: IOException) {
            emit(Resource.error<Boolean>(Constants.Error.General.NETWORK_ERROR))
        }
    }

    override fun signOut(): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.loading(null))
            val result = remoteSource.signOut()
            if (result.operationSuccess) {
                emit(Resource.success(result.operationSuccess))
            } else {
                emit(Resource.error<Boolean>(result.errorCode ?: Constants.Error.Amplify.AMPLIFY_UNEXPECTED_ERROR))
            }
        } catch (e: HttpException) {
            emit(Resource.error<Boolean>(Constants.Error.General.UNEXPECTED_ERROR))
        } catch (e: IOException) {
            emit(Resource.error<Boolean>(Constants.Error.General.NETWORK_ERROR))
        }
    }
}