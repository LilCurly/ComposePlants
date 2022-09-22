package com.example.soplant.application.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TransactionDto(
    @Json(name = "userId")
    val userId: String?,
    @Json(name = "comingFrom")
    val comingFrom: String?,
    @Json(name = "targetId")
    val targetId: String?,
    @Json(name = "amount")
    val amount: String?,
    @Json(name = "status")
    val status: String?,
    @Json(name = "paymentMethod")
    val paymentMethod: String?,
    @Json(name = "createdOn")
    val createdOn: Long?,
    @Json(name = "origin")
    val origin: String?
)
