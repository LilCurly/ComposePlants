package com.example.soplant.domain.entities

data class Wallet(
    val availableAmount: String,
    val frozenAmount: String,
    val pendingAmount: String,
)
