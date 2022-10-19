package com.example.soplant.application.network.mappers

import com.example.soplant.application.network.models.UserDto
import com.example.soplant.domain.entities.User
import com.example.soplant.domain.mappers.Mapper
import javax.inject.Inject

class UserMapper @Inject constructor(private val walletsMapper: WalletsMapper) :
    Mapper<UserDto, User> {
    override fun mapToEntity(model: UserDto): User {
        return User(
            id = model.userId ?: "",
            alias = model.userAlias ?: "",
            profileImageUrl = model.userProfileImageUrl ?: "",
            contactEmail = model.contactEmail ?: "",
            location = model.userLocation ?: "",
            type = model.userType ?: "",
            description = model.userDescription ?: "",
            reviewAverage = model.userReviewAverage ?: 0f,
            isVerified = model.userVerified ?: false,
            sellQuantity = model.userSellQuantity ?: 0,
            kycLevel = model.kycLevel ?: "",
            wallets = walletsMapper.mapToEntity(model.wallets ?: listOf())
        )
    }

    override fun mapFromEntity(entity: User): UserDto {
        return UserDto(
            userId = entity.id,
            userAlias = entity.alias,
            userProfileImageUrl = entity.profileImageUrl,
            contactEmail = entity.contactEmail,
            userLocation = entity.location,
            userType = entity.type,
            userDescription = entity.description,
            userReviewAverage = entity.reviewAverage,
            userVerified = entity.isVerified,
            userSellQuantity = entity.sellQuantity,
            kycLevel = entity.kycLevel,
            wallets = walletsMapper.mapFromEntity(entity.wallets)
        )
    }
}