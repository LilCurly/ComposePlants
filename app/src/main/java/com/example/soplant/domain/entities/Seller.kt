package com.example.soplant.domain.entities

data class Seller(
    val sellerAccountId: String,
    val sellerUserId: String,
    val sellerName: String,
    val sellerReviewAverage: String,
    val sellerImageUrl: String,
    val sellerVerified: Boolean,
    val sellerCountry: String
)