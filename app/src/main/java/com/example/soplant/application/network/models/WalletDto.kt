package com.example.soplant.application.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WalletDto (
    @Json(name = "availableAmount")
    val availableAmount: String,
    @Json(name = "frozenAmount")
    val frozenAmount: String,
    @Json(name = "pendingAmount")
    val pendingAmount: String
)