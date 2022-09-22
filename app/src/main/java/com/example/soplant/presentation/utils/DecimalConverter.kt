package com.example.soplant.presentation.utils

object DecimalConverter {
    fun convertPriceString(price: Float): String {
        return String.format("%.2f", price)
    }
}