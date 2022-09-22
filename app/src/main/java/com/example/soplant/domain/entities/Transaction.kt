package com.example.soplant.domain.entities

data class Transaction(
    val userId: String,
    val comingFrom: String,
    val targetId: String,
    val amount: String,
    val status: String,
    val createdOn: Long,
    val paymentMethod: String,
    val origin: String
)
