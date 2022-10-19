package com.example.soplant.application.network.sources

import com.example.soplant.application.network.mappers.TransactionListMapper
import com.example.soplant.application.network.services.WalletService
import com.example.soplant.application.network.utils.Headers
import com.example.soplant.application.utils.CustomResponse
import com.example.soplant.domain.entities.TransactionList
import javax.inject.Inject

class TransactionsRemoteDataSource @Inject constructor(
    private val service: WalletService,
    private val mapper: TransactionListMapper
) {
    suspend fun getTransactions(lastEvaluatedKey: String?): CustomResponse<TransactionList> {
        val headers = Headers.getDefaultJwtHeader()
        val response = service.getTransactions(headers, lastEvaluatedKey)
        var result: TransactionList? = null
        if (response.isSuccessful && response.body() != null) {
            result = mapper.mapToEntity(response.body()!!)
        }
        return CustomResponse(result, response.isSuccessful, response.code())
    }
}