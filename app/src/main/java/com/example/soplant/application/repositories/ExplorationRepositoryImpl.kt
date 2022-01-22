package com.example.soplant.application.repositories

import com.example.soplant.application.network.sources.ExplorationRemoteDataSource
import com.example.soplant.commons.Constants
import com.example.soplant.domain.repositories.ExplorationRepository
import com.example.soplant.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ExplorationRepositoryImpl @Inject constructor(
    private val remoteDataSource: ExplorationRemoteDataSource
): ExplorationRepository {
    override fun createExploration(): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.loading(null))
            val response = remoteDataSource.createExploration()
            if (response.isSuccessful) {
                emit(Resource.success(true))
            } else {
                emit(Resource.error(Constants.Error.ExplorationApi.FAILED_TO_CREATE))
            }
        } catch (e: HttpException) {
            emit(Resource.error<Boolean>(Constants.Error.General.UNEXPECTED_ERROR))
        } catch (e: IOException) {
            emit(Resource.error<Boolean>(Constants.Error.General.NETWORK_ERROR))
        }
    }
}