package com.example.soplant.application.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WalletDto (
    @Json(name = "type")
    val type: String,
    @Json(name = "amount")
    val amount: String
)