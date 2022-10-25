package com.example.soplant.redux.create_post

import com.example.soplant.redux.State
import javax.inject.Inject

data class CreatePostViewState(
    val filePaths: MutableList<String>,
    val imageTappedIndex: Int,
    val currentPermissionRequest: String,
    val bottomSheetState: BottomSheetState
) : State {
    @Inject
    constructor() : this(mutableListOf(),0, "", BottomSheetState.NONE)

    enum class BottomSheetState {
        NONE,
        ADD_IMAGE,
        EDIT_IMAGE
    }
}