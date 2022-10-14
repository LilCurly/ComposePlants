package com.example.soplant.application.repositories

import com.example.soplant.application.network.sources.ResourceRemoteDataSource
import com.example.soplant.commons.Constants
import com.example.soplant.domain.entities.Country
import com.example.soplant.domain.repositories.ResourceRepository
import com.example.soplant.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ResourceRepositoryImpl @Inject constructor(
    private val remoteDataSource: ResourceRemoteDataSource
): ResourceRepository {
    override fun getCountries(onlySupported: Boolean): Flow<Resource<List<Country>>> = flow {
        try {
            emit(Resource.loading(null))
            val result = remoteDataSource.getCountries(onlySupported)
            if (result.isSuccessful) {
                emit(Resource.success(result.value))
            } else {
                emit(Resource.error<List<Country>>(Constants.Error.General.UNEXPECTED_ERROR))
            }
        } catch (e: HttpException) {
            emit(Resource.error<List<Country>>(Constants.Error.General.UNEXPECTED_ERROR))
        } catch (e: IOException) {
            emit(Resource.error<List<Country>>(Constants.Error.General.NETWORK_ERROR))
        }
    }
}