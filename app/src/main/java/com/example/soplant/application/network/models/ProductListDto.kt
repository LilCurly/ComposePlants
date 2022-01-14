package com.example.soplant.application.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductListDto (
    @Json(name = "products")
    val products: List<ProductDto>?,
    @Json(name = "lastPaginationId")
    val lastPaginationId: String?,
    @Json(name = "isLast")
    val isLast: Boolean?
)