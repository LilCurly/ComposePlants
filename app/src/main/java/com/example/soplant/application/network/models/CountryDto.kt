package com.example.soplant.application.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountryDto(
    @Json(name = "name")
    val name: CountryNameDto?,
    @Json(name = "flags")
    val flags: CountryImageDto?,
    @Json(name = "cca2")
    val cca2: String?
)

@JsonClass(generateAdapter = true)
data class CountryNameDto(
    @Json(name = "common")
    val common: String?
)

@JsonClass(generateAdapter = true)
data class CountryImageDto(
    @Json(name = "png")
    val png: String?
)
