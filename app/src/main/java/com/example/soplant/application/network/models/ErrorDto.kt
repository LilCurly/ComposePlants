package com.example.soplant.application.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorDto(
    @Json(name = "errorType")
    val errorType: String?,
    @Json(name = "errorMessage")
    val errorMessage: String?
)