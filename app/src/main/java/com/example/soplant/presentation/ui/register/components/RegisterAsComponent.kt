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
fun RegisterAsComponent(
    viewModel: RegisterViewModel
) {
    Column(modifier = Modifier.padding(top = 70.dp)) {
        CustomVerticalButtonList(
            modifier = Modifier, title = "I would like to register as a...", values = listOf(
                CustomVerticalButtonListModel(
                    "Individual",
                    RegisterViewState.RegisterAsOptions.USER_TYPE_NATURAL.optionValue
                ),
                CustomVerticalButtonListModel(
                    "Professional",
                    RegisterViewState.RegisterAsOptions.USER_TYPE_LEGAL.optionValue
                )
            )
        ) {
            viewModel.selectRegisterAs(it)
        }
    }
}