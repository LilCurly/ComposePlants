package com.example.soplant.domain.entities

data class TransactionList(
    val transactions: List<Transaction>,
    val lastPaginationId: String,
)
