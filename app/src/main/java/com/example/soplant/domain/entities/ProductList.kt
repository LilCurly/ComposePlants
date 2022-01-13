package com.example.soplant.domain.entities

data class ProductList(
    val products: List<Product>,
    val lastPaginationId: String,
    val isLast: Boolean
)
