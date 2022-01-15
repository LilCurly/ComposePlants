package com.example.soplant.presentation.ui.confirmation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.core.text.isDigitsOnly

@Composable
fun OtpComponent(
    focusManager: FocusManager,
    valueOne: String,
    updateValueOne: (String) -> Unit,
    valueTwo: String,
    updateValueTwo: (String) -> Unit,
    valueThree: String,
    updateValueThree: (String) -> Unit,
    valueFour: String,
    updateValueFour: (String) -> Unit,
    valueFive: String,
    updateValueFive: (String) -> Unit,
    valueSix: String,
    updateValueSix: (String) -> Unit,
    confirmOtp: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ConfirmInputComponent(
            value = valueOne,
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Right) },
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            onValueChange = {
                if (it.isDigitsOnly() && it.isNotEmpty()) {
                    updateValueOne(it)
                    focusManager.moveFocus(FocusDirection.Right)
                }
            },
            onBackspace = {
                updateValueOne("")
            })
        ConfirmInputComponent(
            value = valueTwo,
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Right) }
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            onValueChange = {
                if (it.isDigitsOnly() && it.isNotEmpty()) {
                    updateValueTwo(it)
                    focusManager.moveFocus(FocusDirection.Right)
                }
            },
            onBackspace = {
                updateValueTwo("")
                focusManager.moveFocus(FocusDirection.Left)
            })
        ConfirmInputComponent(
            value = valueThree,
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Right) }
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            onValueChange = {
                if (it.isDigitsOnly() && it.isNotEmpty()) {
                    updateValueThree(it)
                    focusManager.moveFocus(FocusDirection.Right)
                }
            },
            onBackspace = {
                updateValueThree("")
                focusManager.moveFocus(FocusDirection.Left)
            })
        ConfirmInputComponent(
            value = valueFour,
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Right) }
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            onValueChange = {
                if (it.isDigitsOnly() && it.isNotEmpty()) {
                    updateValueFour(it)
                    focusManager.moveFocus(FocusDirection.Right)
                }
            },
            onBackspace = {
                updateValueFour("")
                focusManager.moveFocus(FocusDirection.Left)
            })
        ConfirmInputComponent(
            value = valueFive,
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Right)
                }
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            onValueChange = {
                if (it.isDigitsOnly() && it.isNotEmpty()) {
                    updateValueFive(it)
                    focusManager.moveFocus(FocusDirection.Right)
                }
            },
            onBackspace = {
                updateValueFive("")
                focusManager.moveFocus(FocusDirection.Left)
            })
        ConfirmInputComponent(
            value = valueSix,
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus(true) }
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            onValueChange = {
                if (it.isDigitsOnly() && it.isNotEmpty()) {
                    updateValueSix(it)
                    if (valueOne.isNotEmpty() && valueTwo.isNotEmpty() && valueThree.isNotEmpty() && valueFour.isNotEmpty() && valueFive.isNotEmpty()) {
                        confirmOtp()
                    }
                }
            },
            onBackspace = {
                updateValueSix("")
                focusManager.moveFocus(FocusDirection.Left)
            })
    }
}