package com.example.soplant.application.repositories

import com.example.soplant.application.network.sources.LoginRemoteDataSource
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
            emit(Resource.success(result))
        } catch (e: HttpException) {
            emit(Resource.error<Boolean>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.error<Boolean>("You seem to be disconnected"))
        }
    }

    override fun loginWithSSO() {
        TODO("Not yet implemented")
    }
}