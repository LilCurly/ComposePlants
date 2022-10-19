package com.example.soplant.application.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDto(
    @Json(name = "userId")
    val userId: String?,
    @Json(name = "userAlias")
    val userAlias: String?,
    @Json(name = "userType")
    val userType: String?,
    @Json(name = "userDescription")
    val userDescription: String?,
    @Json(name = "userReviewAverage")
    val userReviewAverage: Float?,
    @Json(name = "userVerified")
    val userVerified: Boolean?,
    @Json(name = "userSellQuantity")
    val userSellQuantity: Int?,
    @Json(name = "userProfileImageUrl")
    val userProfileImageUrl: String?,
    @Json(name = "contactEmail")
    val contactEmail: String?,
    @Json(name = "kycLevel")
    val kycLevel: String?,
    @Json(name = "wallets")
    val wallets: List<WalletDto>?,
    @Json(name = "userLocation")
    val userLocation: String?,
)