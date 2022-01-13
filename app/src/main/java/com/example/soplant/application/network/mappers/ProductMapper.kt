package com.example.soplant.application.network.mappers

import com.example.soplant.application.network.models.ProductDto
import com.example.soplant.domain.entities.Product
import com.example.soplant.domain.mappers.Mapper
import javax.inject.Inject

class ProductMapper @Inject constructor(): Mapper<ProductDto, Product> {
    override fun mapToEntity(model: ProductDto): Product {
        return Product(
            name = model.productName ?: "",
            id = model.productId ?: "",
            description = model.productDescription ?: "",
            sellerId = model.sellerId ?: "",
            sellerName = model.sellerName ?: "",
            headerImageUrl = model.headerImageUrl ?: "",
            complementaryImagesUrl = model.complementaryImagesUrl ?: listOf(),
            quantity = model.quantity ?: 0,
            unitaryPriceSeller = model.unitaryPriceSeller ?: "",
            unitaryPriceDisplay = model.unitaryPriceDisplay ?: "",
            sellerVerified = model.sellerVerified ?: false,
            isVariegated = model.isVariegated?: false,
            isPetFriendly = model.isPetFriendly ?: false,
            type = model.type ?: "",
            isBeginnerFriendly = model.isBeginnerFriendly ?: false,
            size = model.size ?: listOf(),
            weight = model.weight ?: 0,
            sellerCountryCode = model.sellerCountryCode ?: "",
            sellInternationally = model.sellInternationally ?: false,
            createdOn = model.createdOn ?: 0,
            waterLevel = model.waterLevel ?: 0,
            lightLevel = model.lightLevel ?: 0,
            maturity = model.maturity ?: 0
        )
    }

    override fun mapFromEntity(entity: Product): ProductDto {
        return ProductDto(
            productName = entity.name,
            productId = entity.id,
            productDescription = entity.description,
            sellerId = entity.sellerId,
            sellerName = entity.sellerName,
            headerImageUrl = entity.headerImageUrl,
            complementaryImagesUrl = entity.complementaryImagesUrl,
            quantity = entity.quantity,
            unitaryPriceSeller = entity.unitaryPriceSeller,
            unitaryPriceDisplay = entity.unitaryPriceDisplay,
            sellerVerified = entity.sellerVerified,
            isVariegated = entity.isVariegated ,
            isPetFriendly = entity.isPetFriendly,
            type = entity.type,
            isBeginnerFriendly = entity.isBeginnerFriendly,
            size = entity.size,
            weight = entity.weight,
            sellerCountryCode = entity.sellerCountryCode,
            sellInternationally = entity.sellInternationally,
            createdOn = entity.createdOn,
            waterLevel = entity.waterLevel,
            lightLevel = entity.lightLevel,
            maturity = entity.maturity
        )
    }
}