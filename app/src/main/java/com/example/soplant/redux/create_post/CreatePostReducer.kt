package com.example.soplant.redux.create_post

import com.example.soplant.redux.Reducer
import javax.inject.Inject

class CreatePostReducer @Inject constructor() : Reducer<CreatePostAction, CreatePostViewState> {
    override fun reduce(
        previousState: CreatePostViewState,
        currentAction: CreatePostAction
    ): CreatePostViewState {
        return when (currentAction) {
            is CreatePostAction.AddFilePath -> {
                val filePaths = mutableListOf<String>()
                filePaths.addAll(previousState.filePaths)
                if (filePaths.size < 5) {
                    filePaths.add(currentAction.filePath)
                }
                previousState.copy(filePaths = filePaths)
            }
            is CreatePostAction.AddMultipleFilePaths -> {
                val filePaths = mutableListOf<String>()
                filePaths.addAll(previousState.filePaths)
                filePaths.addAll(currentAction.filePaths)
                previousState.copy(filePaths = filePaths)
            }
            is CreatePostAction.EditFilePath -> {
                val filePaths = mutableListOf<String>()
                previousState.filePaths.forEachIndexed { i, value ->
                    if (i == previousState.imageTappedIndex) {
                        filePaths.add(currentAction.filePath)
                    } else {
                        filePaths.add(value)
                    }
                }
                previousState.copy(filePaths = filePaths)
            }
            is CreatePostAction.ClearPermission -> {
                previousState.copy(currentPermissionRequest = "")
            }
            is CreatePostAction.TappedImage -> {
                previousState.copy(imageTappedIndex = currentAction.index)
            }
            is CreatePostAction.AskPermission -> {
                previousState.copy(currentPermissionRequest = currentAction.permission)
            }
            is CreatePostAction.CloseBottomSheet -> {
                previousState.copy(bottomSheetState = CreatePostViewState.BottomSheetState.NONE)
            }
            is CreatePostAction.OpenAddImageBottomSheet -> {
                previousState.copy(bottomSheetState = CreatePostViewState.BottomSheetState.ADD_IMAGE)
            }
            is CreatePostAction.OpenEditImageBottomSheet -> {
                previousState.copy(bottomSheetState = CreatePostViewState.BottomSheetState.EDIT_IMAGE)
            }
        }
    }
}