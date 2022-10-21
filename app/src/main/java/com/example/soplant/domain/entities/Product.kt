package com.example.soplant.domain.entities

data class Product(
    val name: String,
    val id: String,
    val description: String,
    val headerImageUrl: String,
    val complementaryImagesUrl: List<String>,
    val quantity: Int,
    val unitaryPriceSeller: String,
    val unitaryPriceDisplay: String,
    val isVariegated: Boolean,
    val maturity: Int,
    val seller: Seller,
    val plant: Plant,
    val deliveryPricesRange: DeliveryPricesRange,
    val deliveryCountriesAdditional: DeliveryCountriesAdditional,
    val sellInternationally: Boolean,
    val createdOn: Long
)
