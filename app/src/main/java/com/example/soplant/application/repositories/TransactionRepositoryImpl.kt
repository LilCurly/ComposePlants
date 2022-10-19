package com.example.soplant.application.repositories

import com.example.soplant.application.network.sources.TransactionsRemoteDataSource
import com.example.soplant.commons.Constants
import com.example.soplant.domain.entities.TransactionList
import com.example.soplant.domain.repositories.TransactionRepository
import com.example.soplant.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val remoteDataSource: TransactionsRemoteDataSource
): TransactionRepository {
    override fun getTransactions(lastEvaluatedKey: String?): Flow<Resource<TransactionList>> = flow {
        try {
            emit(Resource.loading(null))
            val response = remoteDataSource.getTransactions(lastEvaluatedKey)
            if (response.isSuccessful) {
                emit(Resource.success(response.value))
            } else {
                emit(Resource.error(Constants.Error.TransactionsApi.FAILED_TO_LOAD, response.error))
            }
        } catch (e: HttpException) {
            emit(Resource.error<TransactionList>(Constants.Error.General.UNEXPECTED_ERROR, null))
        } catch (e: IOException) {
            emit(Resource.error<TransactionList>(Constants.Error.General.NETWORK_ERROR, null))
        }
    }
}