package com.example.soplant.presentation.ui.register.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.soplant.presentation.ui.custom.CustomVerticalButtonList
import com.example.soplant.presentation.ui.custom.CustomVerticalButtonListModel
import com.example.soplant.presentation.ui.register.RegisterViewModel
import com.example.soplant.redux.register.RegisterViewState

@Composable
fun LegalEntityComponent(
    viewModel: RegisterViewModel
) {
    Column(modifier = Modifier.padding(top = 70.dp)) {
        CustomVerticalButtonList(
            modifier = Modifier, title = "With the legal status...", values = listOf(
                CustomVerticalButtonListModel(
                    "Soletrader",
                    RegisterViewState.LegalEntityOptions.SOLETRADER.optionValue
                ),
                CustomVerticalButtonListModel(
                    "Business",
                    RegisterViewState.LegalEntityOptions.BUSINESS.optionValue
                ),
                CustomVerticalButtonListModel(
                    "Organization",
                    RegisterViewState.LegalEntityOptions.ORGANIZATION.optionValue
                )
            )
        ) {
            viewModel.selectLegalEntity(it)
        }
    }
}