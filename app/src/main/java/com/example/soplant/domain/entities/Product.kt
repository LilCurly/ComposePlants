package com.example.soplant.domain.entities

data class Product(
    val name: String,
    val id: String,
    val description: String,
    val sellerId: String,
    val sellerName: String,
    val headerImageUrl: String,
    val complementaryImagesUrl: List<String>,
    val quantity: Int,
    val unitaryPriceSeller: String,
    val unitaryPriceDisplay: String,
    val sellerVerified: Boolean,
    val isVariegated: Boolean,
    val isPetFriendly: Boolean,
    val type: String,
    val isBeginnerFriendly: Boolean,
    val size: List<Int>,
    val weight: Int,
    val sellerCountryCode: String,
    val sellInternationally: Boolean,
    val createdOn: Long,
    val waterLevel: Int,
    val lightLevel: Int,
    val maturity: Int
)
