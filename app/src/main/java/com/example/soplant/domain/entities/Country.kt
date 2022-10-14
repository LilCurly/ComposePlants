package com.example.soplant.domain.entities

data class Country(
    val name: CountryName,
    val image: CountryImage,
    val code: String
)

data class CountryName(
    val common: String
)

data class CountryImage(
    val png: String
)
