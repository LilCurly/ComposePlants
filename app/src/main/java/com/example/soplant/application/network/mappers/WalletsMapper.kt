package com.example.soplant.application.network.mappers

import com.example.soplant.application.network.models.WalletDto
import com.example.soplant.domain.entities.Wallet
import com.example.soplant.domain.mappers.Mapper
import javax.inject.Inject

class WalletsMapper @Inject constructor(private val walletMapper: WalletMapper) : Mapper<List<WalletDto>, List<Wallet>> {
    override fun mapToEntity(model: List<WalletDto>): List<Wallet> {
        return model.map {
            walletMapper.mapToEntity(it)
        }
    }

    override fun mapFromEntity(entity: List<Wallet>): List<WalletDto> {
        return entity.map {
            walletMapper.mapFromEntity(it)
        }
    }
}