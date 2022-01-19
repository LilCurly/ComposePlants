package com.example.soplant.application.utils

data class CustomResponse<T>(
    val value: T?,
    val isSuccessful: Boolean,
    val code: Int
)