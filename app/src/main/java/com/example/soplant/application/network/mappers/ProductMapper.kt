package com.example.soplant.application.network.mappers

import com.example.soplant.application.network.models.ProductDto
import com.example.soplant.domain.entities.Product
import com.example.soplant.domain.mappers.Mapper
import javax.inject.Inject

class ProductMapper @Inject constructor(
    private val sellerMapper: SellerMapper,
    private val plantMapper: PlantMapper,
    private val deliveryPricesRangeMapper: DeliveryPricesRangeMapper,
    private val deliveryCountriesAdditionalMapper: DeliveryCountriesAdditionalMapper
) : Mapper<ProductDto, Product> {
    override fun mapToEntity(model: ProductDto): Product {
        return Product(
            name = model.productName ?: "",
            id = model.productId ?: "",
            description = model.productDescription ?: "",
            headerImageUrl = model.headerImageUrl ?: "",
            complementaryImagesUrl = model.complementaryImagesUrl ?: listOf(),
            quantity = model.quantity ?: 0,
            unitaryPriceSeller = model.unitaryPriceSeller ?: "",
            unitaryPriceDisplay = model.unitaryPriceDisplay ?: "",
            isVariegated = model.isVariegated ?: false,
            sellInternationally = model.sellInternationally ?: false,
            createdOn = model.createdOn ?: 0,
            maturity = model.maturity ?: 0,
            plant = plantMapper.mapToEntity(model.plantFamily),
            deliveryPricesRange = deliveryPricesRangeMapper.mapToEntity(model.deliveryPricesRange),
            deliveryCountriesAdditional = deliveryCountriesAdditionalMapper.mapToEntity(model.deliveryCountriesAdditional),
            seller = sellerMapper.mapToEntity(model.sellerDetail)
        )
    }

    override fun mapFromEntity(entity: Product): ProductDto {
        return ProductDto(
            productName = entity.name,
            productId = entity.id,
            productDescription = entity.description,
            headerImageUrl = entity.headerImageUrl,
            complementaryImagesUrl = entity.complementaryImagesUrl,
            quantity = entity.quantity,
            unitaryPriceSeller = entity.unitaryPriceSeller,
            unitaryPriceDisplay = entity.unitaryPriceDisplay,
            isVariegated = entity.isVariegated,
            sellInternationally = entity.sellInternationally,
            createdOn = entity.createdOn,
            maturity = entity.maturity,
            plantFamily = plantMapper.mapFromEntity(entity.plant),
            deliveryPricesRange = deliveryPricesRangeMapper.mapFromEntity(entity.deliveryPricesRange),
            deliveryCountriesAdditional = deliveryCountriesAdditionalMapper.mapFromEntity(entity.deliveryCountriesAdditional),
            sellerDetail = sellerMapper.mapFromEntity(entity.seller)
        )
    }
}