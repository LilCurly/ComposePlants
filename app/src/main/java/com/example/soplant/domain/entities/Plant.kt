package com.example.soplant.domain.entities

data class Plant(
    val id: String,
    val isPetFriendly: Boolean,
    val typeDisplay: String,
    val typeFilter: String,
    val isBeginnerFriendly: Boolean,
    val waterLevel: Int,
    val lightLevel: Int,
    val isIndoor: Boolean
)
