package com.example.soplant.presentation.utils

import androidx.annotation.DrawableRes
import com.example.soplant.R
import com.example.soplant.commons.Constants

object IconFinder {
    fun findTransactionIcon(origin: String): Int {
        return when(origin) {
            Constants.Model.Transaction.Origin.BOOST -> R.drawable.icon_transaction_down
            Constants.Model.Transaction.Origin.BUY_PRODUCT -> R.drawable.icon_transaction_down
            Constants.Model.Transaction.Origin.SELL_PRODUCT -> R.drawable.icon_transaction_up
            Constants.Model.Transaction.Origin.WITHDRAWAL -> R.drawable.icon_transaction_down
            Constants.Model.Transaction.Origin.FUND_WALLET -> R.drawable.icon_transaction_up
            else -> R.drawable.icon_transaction_up
        }
    }
}