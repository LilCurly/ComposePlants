package com.example.soplant.redux.create_post

import com.example.soplant.redux.Action

sealed class CreatePostAction : Action {
    data class AddFilePath(val filePath: String) : CreatePostAction()
    data class AddMultipleFilePaths(val filePaths: List<String>) : CreatePostAction()
    data class EditFilePath(val filePath: String) : CreatePostAction()
    data class AskPermission(val permission: String) : CreatePostAction()
    object ClearPermission : CreatePostAction()
    data class TappedImage(val index: Int) : CreatePostAction()
    object CloseBottomSheet : CreatePostAction()
    object OpenAddImageBottomSheet : CreatePostAction()
    object OpenEditImageBottomSheet : CreatePostAction()
}