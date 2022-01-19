package com.example.soplant.application.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountryDto(
    @Json(name = "name")
    val name: String?,
    @Json(name = "image")
    val image: String?,
    @Json(name = "code")
    val code: String?
)