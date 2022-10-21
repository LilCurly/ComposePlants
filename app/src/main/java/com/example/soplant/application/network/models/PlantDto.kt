package com.example.soplant.application.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlantDto(
    @Json(name = "plantId")
    val plantId: String?,
    @Json(name = "isPetFriendly")
    val isPetFriendly: Boolean?,
    @Json(name = "typeDisplay")
    val typeDisplay: String?,
    @Json(name = "typeFilter")
    val typeFilter: String?,
    @Json(name = "isBeginnerFriendly")
    val isBeginnerFriendly: Boolean?,
    @Json(name = "waterLevel")
    val waterLevel: Int?,
    @Json(name = "lightLevel")
    val lightLevel: Int?,
    @Json(name = "isIndoor")
    val isIndoor: Boolean?,
)