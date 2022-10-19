package com.example.soplant.application.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UsersDto(
    @Json(name = "users")
    val users: List<UserDto>
)