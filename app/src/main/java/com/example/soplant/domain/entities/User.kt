package com.example.soplant.domain.entities

data class User(
    val id: String,
    val alias: String,
    val profileImageUrl: String,
    val contactEmail: String,
    val location: String,
    val type: String,
    val description: String,
    val reviewAverage: Float,
    val isVerified: Boolean,
    val sellQuantity: Int,
    val kycLevel: String,
    val wallets: List<Wallet>
)