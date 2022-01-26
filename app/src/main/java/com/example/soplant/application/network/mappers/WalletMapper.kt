package com.example.soplant.application.network.mappers

import com.example.soplant.application.network.models.WalletDto
import com.example.soplant.domain.entities.Wallet
import com.example.soplant.domain.mappers.Mapper
import javax.inject.Inject

class WalletMapper @Inject constructor(): Mapper<WalletDto, Wallet> {
    override fun mapToEntity(model: WalletDto): Wallet {
        return Wallet(
            model.availableAmount,
            model.frozenAmount,
            model.pendingAmount
        )
    }

    override fun mapFromEntity(entity: Wallet): WalletDto {
        return WalletDto(
            entity.availableAmount,
            entity.frozenAmount,
            entity.pendingAmount
        )
    }
}