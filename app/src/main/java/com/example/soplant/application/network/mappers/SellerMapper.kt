package com.example.soplant.application.network.mappers

import com.example.soplant.application.network.models.SellerDto
import com.example.soplant.domain.entities.Seller
import com.example.soplant.domain.mappers.Mapper
import javax.inject.Inject

class SellerMapper @Inject constructor(): Mapper<SellerDto?, Seller> {
    override fun mapToEntity(model: SellerDto?): Seller {
        return Seller(
            sellerAccountId = model?.sellerAccountId ?: "",
            sellerUserId = model?.sellerUserId ?: "",
            sellerImageUrl = model?.sellerImageUrl ?: "",
            sellerReviewAverage = model?.sellerReviewAverage ?: "",
            sellerCountry = model?.sellerCountry ?: "",
            sellerName = model?.sellerName ?: "",
            sellerVerified = model?.sellerVerified ?: false
        )
    }

    override fun mapFromEntity(entity: Seller): SellerDto? {
        return SellerDto(
            sellerVerified = entity.sellerVerified,
            sellerName = entity.sellerName,
            sellerCountry = entity.sellerCountry,
            sellerReviewAverage = entity.sellerReviewAverage,
            sellerImageUrl = entity.sellerImageUrl,
            sellerUserId = entity.sellerUserId,
            sellerAccountId = entity.sellerAccountId
        )
    }
}