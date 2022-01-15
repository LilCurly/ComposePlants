package com.example.soplant.redux.confirmation

import com.example.soplant.redux.Action

sealed class ConfirmationAction: Action {
    data class ConfirmationFailed(val errorCode: String?): ConfirmationAction()
    object ConfirmationSucceeded: ConfirmationAction()
    object ConfirmationProcessStarted: ConfirmationAction()
    object ConfirmClicked: ConfirmationAction()
    object ResendClicked: ConfirmationAction()
    object ResendProcessStarted: ConfirmationAction()
    data class ResendFailed(val errorCode: String?): ConfirmationAction()
    object ResendSucceeded: ConfirmationAction()
    object NavigateToUserWall: ConfirmationAction()
    object NavigateToLogin: ConfirmationAction()
    object AuthUser: ConfirmationAction()
    object AuthFailed: ConfirmationAction()
    object AuthSucceeded: ConfirmationAction()
    data class UpdatingValueOne(val newValue: String): ConfirmationAction()
    data class UpdatingValueTwo(val newValue: String): ConfirmationAction()
    data class UpdatingValueThree(val newValue: String): ConfirmationAction()
    data class UpdatingValueFour(val newValue: String): ConfirmationAction()
    data class UpdatingValueFive(val newValue: String): ConfirmationAction()
    data class UpdatingValueSix(val newValue: String): ConfirmationAction()
    data class SetUserEmail(val userEmail: String): ConfirmationAction()
    data class SetUserPassword(val userPassword: String): ConfirmationAction()
}