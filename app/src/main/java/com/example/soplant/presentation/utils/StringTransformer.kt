package com.example.soplant.presentation.utils

import com.example.soplant.commons.Constants

object StringTransformer {
    fun convertTransactionOriginToString(origin: String): String {
        return when(origin) {
            Constants.Model.Transaction.Origin.BOOST -> "Boost"
            Constants.Model.Transaction.Origin.BUY_PRODUCT -> "Plant bought"
            Constants.Model.Transaction.Origin.SELL_PRODUCT -> "Plant sold"
            Constants.Model.Transaction.Origin.FUND_WALLET -> "Added balance"
            Constants.Model.Transaction.Origin.WITHDRAWAL -> "Withdrawal"
            else -> ""
        }
    }

    fun convertTransactionPrice(origin: String, amount: String): String {
        return when(origin) {
            Constants.Model.Transaction.Origin.BOOST -> "-${amount}"
            Constants.Model.Transaction.Origin.BUY_PRODUCT -> "-${amount}"
            Constants.Model.Transaction.Origin.SELL_PRODUCT -> "+${amount}"
            Constants.Model.Transaction.Origin.FUND_WALLET -> "+${amount}"
            Constants.Model.Transaction.Origin.WITHDRAWAL -> "-${amount}"
            else -> ""
        }
    }
}