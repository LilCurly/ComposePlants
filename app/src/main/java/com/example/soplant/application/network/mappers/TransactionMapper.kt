package com.example.soplant.application.network.mappers

import com.example.soplant.application.network.models.TransactionDto
import com.example.soplant.domain.entities.Transaction
import com.example.soplant.domain.mappers.Mapper
import javax.inject.Inject

class TransactionMapper @Inject constructor(): Mapper<TransactionDto, Transaction> {
    override fun mapToEntity(model: TransactionDto): Transaction {
        return Transaction(
            model.userId ?: "",
            model.comingFrom ?: "",
            model.targetId ?: "",
            model.amount ?: "",
            model.status ?: "",
            model.createdOn ?: 0,
            model.paymentMethod ?: "",
            model.origin ?: ""
        )
    }

    override fun mapFromEntity(entity: Transaction): TransactionDto {
        return TransactionDto(
            entity.userId,
            entity.comingFrom,
            entity.targetId,
            entity.amount,
            entity.status,
            entity.paymentMethod,
            entity.createdOn,
            entity.origin
        )
    }
}