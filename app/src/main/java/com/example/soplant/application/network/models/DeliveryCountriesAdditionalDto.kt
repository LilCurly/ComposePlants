package com.example.soplant.application.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeliveryCountriesAdditionalDto(
    @Json(name = "countries")
    val countries: List<String>?,
    @Json(name = "additionalDeliveryPrice")
    val additionalDeliveryPrice: String?
)
