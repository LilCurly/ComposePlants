package com.example.soplant.application.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SellerDto(
    @Json(name = "sellerAccountId")
    val sellerAccountId: String?,
    @Json(name = "sellerUserId")
    val sellerUserId: String?,
    @Json(name = "sellerName")
    val sellerName: String?,
    @Json(name = "sellerReviewAverage")
    val sellerReviewAverage: String?,
    @Json(name = "sellerImageUrl")
    val sellerImageUrl: String?,
    @Json(name = "sellerVerified")
    val sellerVerified: Boolean?,
    @Json(name = "sellerCountry")
    val sellerCountry: String?,
)