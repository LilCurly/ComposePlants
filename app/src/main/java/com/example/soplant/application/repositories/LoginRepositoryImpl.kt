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
                emit(Resource.error<Boolean>(result.errorCode ?: Constants.Error.Amplify.AMPLIFY_UNEXPECTED_ERROR, null))
            }
        } catch (e: HttpException) {
            emit(Resource.error<Boolean>(Constants.Error.General.UNEXPECTED_ERROR, null))
        } catch (e: IOException) {
            emit(Resource.error<Boolean>(Constants.Error.General.NETWORK_ERROR, null))
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
                emit(Resource.error<Boolean>(result.errorCode ?: Constants.Error.Amplify.AMPLIFY_UNEXPECTED_ERROR, null))
            }
        } catch (e: HttpException) {
            emit(Resource.error<Boolean>(Constants.Error.General.UNEXPECTED_ERROR, null))
        } catch (e: IOException) {
            emit(Resource.error<Boolean>(Constants.Error.General.NETWORK_ERROR, null))
        }
    }

    override fun validateUser(email: String, code: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.loading(null))
            val result = remoteSource.validateUser(email, code)
            if (result.operationSuccess) {
                emit(Resource.success(result.operationSuccess))
            } else {
                emit(Resource.error<Boolean>(result.errorCode ?: Constants.Error.Amplify.AMPLIFY_UNEXPECTED_ERROR, null))
            }
        } catch (e: HttpException) {
            emit(Resource.error<Boolean>(Constants.Error.General.UNEXPECTED_ERROR, null))
        } catch (e: IOException) {
            emit(Resource.error<Boolean>(Constants.Error.General.NETWORK_ERROR, null))
        }
    }

    override fun resendCode(email: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.loading(null))
            val result = remoteSource.resendCode(email)
            if (result.operationSuccess) {
                emit(Resource.success(result.operationSuccess))
            } else {
                emit(Resource.error<Boolean>(result.errorCode ?: Constants.Error.Amplify.AMPLIFY_UNEXPECTED_ERROR, null))
            }
        } catch (e: HttpException) {
            emit(Resource.error<Boolean>(Constants.Error.General.UNEXPECTED_ERROR, null))
        } catch (e: IOException) {
            emit(Resource.error<Boolean>(Constants.Error.General.NETWORK_ERROR, null))
        }
    }

    override fun resetPassword(email: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.loading(null))
            val result = remoteSource.resetPassword(email)
            if (result.operationSuccess) {
                emit(Resource.success(result.operationSuccess))
            } else {
                emit(Resource.error<Boolean>(result.errorCode ?: Constants.Error.Amplify.AMPLIFY_UNEXPECTED_ERROR, null))
            }
        } catch (e: HttpException) {
            emit(Resource.error<Boolean>(Constants.Error.General.UNEXPECTED_ERROR, null))
        } catch (e: IOException) {
            emit(Resource.error<Boolean>(Constants.Error.General.NETWORK_ERROR, null))
        }
    }

    override fun confirmReset(newPassword: String, code: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.loading(null))
            val result = remoteSource.confirmReset(newPassword, code)
            if (result.operationSuccess) {
                emit(Resource.success(result.operationSuccess))
            } else {
                emit(Resource.error<Boolean>(result.errorCode ?: Constants.Error.Amplify.AMPLIFY_UNEXPECTED_ERROR, null))
            }
        } catch (e: HttpException) {
            emit(Resource.error<Boolean>(Constants.Error.General.UNEXPECTED_ERROR, null))
        } catch (e: IOException) {
            emit(Resource.error<Boolean>(Constants.Error.General.NETWORK_ERROR, null))
        }
    }

    override fun federateSignIn(legalName: String, firstName: String, lastName: String, registerAs: String, legalType: String, location: String, userImageUrl: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.loading(null))
            val result = remoteSource.federateSignIn(legalName, firstName, lastName, registerAs, legalType, location, userImageUrl)
            if (result.operationSuccess) {
                emit(Resource.success(result.operationSuccess))
            } else {
                emit(Resource.error<Boolean>(result.errorCode ?: Constants.Error.Amplify.AMPLIFY_UNEXPECTED_ERROR, null))
            }
        } catch (e: HttpException) {
            emit(Resource.error<Boolean>(Constants.Error.General.UNEXPECTED_ERROR, null))
        } catch (e: IOException) {
            emit(Resource.error<Boolean>(Constants.Error.General.NETWORK_ERROR, null))
        }
    }

    override fun signOut(): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.loading(null))
            val result = remoteSource.signOut()
            if (result.operationSuccess) {
                emit(Resource.success(result.operationSuccess))
            } else {
                emit(Resource.error<Boolean>(result.errorCode ?: Constants.Error.Amplify.AMPLIFY_UNEXPECTED_ERROR, null))
            }
        } catch (e: HttpException) {
            emit(Resource.error<Boolean>(Constants.Error.General.UNEXPECTED_ERROR, null))
        } catch (e: IOException) {
            emit(Resource.error<Boolean>(Constants.Error.General.NETWORK_ERROR, null))
        }
    }
}