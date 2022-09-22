package com.example.soplant.application.network.mappers

import com.example.soplant.application.network.models.TransactionListDto
import com.example.soplant.domain.entities.Transaction
import com.example.soplant.domain.entities.TransactionList
import com.example.soplant.domain.mappers.Mapper
import javax.inject.Inject

class TransactionListMapper @Inject constructor(private val transactionMapper: TransactionMapper): Mapper<TransactionListDto, TransactionList> {
    override fun mapToEntity(model: TransactionListDto): TransactionList {
        return TransactionList(
            model.results?.map {
                transactionMapper.mapToEntity(it)
            } ?: listOf(),
            model.lastEvaluatedKey ?: ""
        )
    }

    override fun mapFromEntity(entity: TransactionList): TransactionListDto {
        return TransactionListDto(
            entity.transactions.map {
                transactionMapper.mapFromEntity(it)
            },
            entity.lastPaginationId
        )
    }
}