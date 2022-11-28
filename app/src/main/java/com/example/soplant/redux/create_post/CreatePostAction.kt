package com.example.soplant.redux.create_post

import com.example.soplant.redux.Action

sealed class CreatePostAction : Action {
    object ClearPermission : CreatePostAction()
    data class AskPermission(val permission: String) : CreatePostAction()
    object CloseBottomSheet : CreatePostAction()
    object OpenAddImageBottomSheet : CreatePostAction()
    object OpenEditImageBottomSheet : CreatePostAction()
    data class NavigateToStep(val nextStep: Int) : CreatePostAction()

    // Post Images
    data class AddFilePath(val filePath: String) : CreatePostAction()
    data class AddMultipleFilePaths(val filePaths: List<String>) : CreatePostAction()
    data class EditFilePath(val filePath: String) : CreatePostAction()
    data class TappedImage(val index: Int) : CreatePostAction()
    data class DeleteFilePath(val index: Int) : CreatePostAction()

    // Post information
    data class EditPostTitle(val newPostTitle: String) : CreatePostAction()
    data class EditPostDescription(val newPostDescription: String) : CreatePostAction()

    // Plant information
    data class SelectPlantType(val newPlantType: String) : CreatePostAction()
    data class SelectPlantLightLevel(val newLightLevel: Int) : CreatePostAction()
    data class SelectPlantWaterLevel(val newWaterLevel: Int) : CreatePostAction()
    data class SelectPlantGrowLevel(val newGrowLevel: Int) : CreatePostAction()
    object SwitchPlantIsUserFriendly : CreatePostAction()
    object SwitchPlantIsVariegation : CreatePostAction()

    // Plant unitary price
    data class EditUnitaryPrice(val char: String) : CreatePostAction()
    object DeleteUnitaryPrice : CreatePostAction()
}