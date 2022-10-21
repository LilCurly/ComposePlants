package com.example.soplant.domain.entities

data class ProductList(
    val products: List<Product>,
    val lastPaginationTimestamp: Long?,
    val isLast: Boolean
)
