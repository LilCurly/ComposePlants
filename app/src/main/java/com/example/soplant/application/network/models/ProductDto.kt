package com.example.soplant.application.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductDto(
    @Json(name = "productId")
    val productId: String?,
    @Json(name = "productName")
    val productName: String?,
    @Json(name = "productDescription")
    val productDescription: String?,
    @Json(name = "sellerDetail")
    val sellerDetail: SellerDto?,
    @Json(name = "headerImageUrl")
    val headerImageUrl: String?,
    @Json(name = "complementaryImagesUrl")
    val complementaryImagesUrl: List<String>?,
    @Json(name = "quantity")
    val quantity: Int?,
    @Json(name = "unitaryPriceSeller")
    val unitaryPriceSeller: String?,
    @Json(name = "unitaryPriceDisplay")
    val unitaryPriceDisplay: String?,
    @Json(name = "isVarigation")
    val isVariegated: Boolean?,
    @Json(name = "plantFamily")
    val plantFamily: PlantDto?,
    @Json(name = "maturity")
    val maturity: Int?,
    @Json(name = "deliveryPricesRange")
    val deliveryPricesRange: DeliveryPricesRangeDto?,
    @Json(name = "deliveryCountriesAdditional")
    val deliveryCountriesAdditional: DeliveryCountriesAdditionalDto?,
    @Json(name = "sellInternationally")
    val sellInternationally: Boolean?,
    @Json(name = "createdOn")
    val createdOn: Long?
)