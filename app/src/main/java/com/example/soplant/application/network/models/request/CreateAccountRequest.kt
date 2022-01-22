package com.example.soplant.application.network.models.request

data class CreateAccountRequest(
    val userAlias: String,
    val userCountry: String,
    val userImageUrl: String?
)
