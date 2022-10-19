package com.example.soplant.application.utils

import com.example.soplant.domain.entities.ApiError

data class CustomResponse<T>(
    val value: T? = null,
    val isSuccessful: Boolean,
    val code: Int,
    val error: ApiError? = null
)