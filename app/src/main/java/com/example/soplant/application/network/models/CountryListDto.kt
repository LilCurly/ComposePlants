package com.example.soplant.application.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountryListDto(
    @Json(name = "results")
    val results: List<CountryDto>
)