package com.example.soplant.application.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductDto(
    @Json(name = "id")
    val productId: String?,
    @Json(name = "productName")
    val productName: String?,
    @Json(name = "productDescription")
    val productDescription: String?,
    @Json(name = "sellerId")
    val sellerId: String?,
    @Json(name = "sellerName")
    val sellerName: String?,
    @Json(name = "sellerImageUrl")
    val sellerImageUrl: String?,
    @Json(name = "sellerReviewAverage")
    val sellerReviewAverage: String?,
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
    @Json(name = "sellerVerified")
    val sellerVerified: Boolean?,
    @Json(name = "isVarigation")
    val isVariegated: Boolean?,
    @Json(name = "isPetFriendly")
    val isPetFriendly: Boolean?,
    @Json(name = "type")
    val type: String?,
    @Json(name = "isBeginnerFriendly")
    val isBeginnerFriendly: Boolean?,
    @Json(name = "size")
    val size: Int?,
    @Json(name = "weight")
    val weight: Int?,
    @Json(name = "sellerCountryCode")
    val sellerCountryCode: String?,
    @Json(name = "sellInternationally")
    val sellInternationally: Boolean?,
    @Json(name = "createdOn")
    val createdOn: Long?,
    @Json(name = "lightLevel")
    val lightLevel: Int?,
    @Json(name = "waterLevel")
    val waterLevel: Int?,
    @Json(name = "maturity")
    val maturity: Int?
)