package com.example.soplant.application.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeliveryPricesRangeDto(
    @Json(name = "rangeMin")
    val rangeMin: Int?,
    @Json(name = "rangeMax")
    val rangeMax: Int?,
    @Json(name = "price")
    val price: String?,
)
