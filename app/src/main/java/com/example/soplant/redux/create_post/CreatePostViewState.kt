package com.example.soplant.redux.create_post

import com.example.soplant.redux.State
import javax.inject.Inject

data class CreatePostViewState(
    val currentStep: Int,
    val previousStep: Int,
    val lastHighestStep: Int,
    val currentPermissionRequest: String,
    val bottomSheetState: BottomSheetState,
    // Post Images
    val filePaths: MutableList<String>,
    val imageTappedIndex: Int,
    // Post information
    val postTitle: String,
    val postDescription: String,
    // Plant information
    val plantType: String,
    val plantLightLevel: Int,
    val plantWaterLevel: Int,
    val plantGrowLevel: Int,
    val plantIsUserFriendly: Boolean,
    val plantIsVariegation: Boolean
) : State {
    @Inject
    constructor() : this(
        0,
        0,
        0,
        "",
        BottomSheetState.NONE,
        mutableListOf(),
        0,
        "",
        "",
        "",
        0,
        0,
        0,
        false,
        false
    )

    enum class BottomSheetState {
        NONE,
        ADD_IMAGE,
        EDIT_IMAGE
    }
}