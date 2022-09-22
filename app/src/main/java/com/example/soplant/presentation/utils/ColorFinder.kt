package com.example.soplant.presentation.utils

import androidx.compose.ui.graphics.Color
import com.example.soplant.R
import com.example.soplant.commons.Constants
import com.example.soplant.presentation.theme.GreenSuccess
import com.example.soplant.presentation.theme.RedError

object ColorFinder {
    fun findTransactionColor(origin: String): Color {
        return when(origin) {
            Constants.Model.Transaction.Origin.BOOST -> RedError
            Constants.Model.Transaction.Origin.BUY_PRODUCT -> RedError
            Constants.Model.Transaction.Origin.SELL_PRODUCT -> GreenSuccess
            Constants.Model.Transaction.Origin.WITHDRAWAL -> RedError
            Constants.Model.Transaction.Origin.FUND_WALLET -> GreenSuccess
            else -> GreenSuccess
        }
    }
}