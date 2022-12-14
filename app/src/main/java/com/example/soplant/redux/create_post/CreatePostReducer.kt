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
                canContinueState(previousState.copy(filePaths = filePaths))
            }
            is CreatePostAction.AddMultipleFilePaths -> {
                val filePaths = mutableListOf<String>()
                filePaths.addAll(previousState.filePaths)
                filePaths.addAll(currentAction.filePaths)
                canContinueState(previousState.copy(filePaths = filePaths))
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
            is CreatePostAction.DeleteFilePath -> {
                if (previousState.filePaths.size - 1 >= currentAction.index) {
                    val filePaths = mutableListOf<String>()
                    previousState.filePaths.forEachIndexed { i, value ->
                        if (i != currentAction.index) {
                            filePaths.add(value)
                        }
                    }
                    return canContinueState(previousState.copy(filePaths = filePaths))
                }
                return previousState
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
            is CreatePostAction.NavigateToStep -> {
                val previousStep = previousState.currentStep
                canContinueState(
                    previousState.copy(
                        previousStep = previousStep,
                        currentStep = currentAction.nextStep,
                        lastHighestStep = if (currentAction.nextStep > previousState.lastHighestStep) currentAction.nextStep else previousState.lastHighestStep
                    )
                )
            }
            is CreatePostAction.EditPostTitle -> {
                canContinueState(
                    previousState.copy(postTitle = currentAction.newPostTitle)
                )
            }
            is CreatePostAction.EditPostDescription -> {
                canContinueState(
                    previousState.copy(postDescription = currentAction.newPostDescription)
                )
            }
            is CreatePostAction.SelectPlantType -> {
                canContinueState(
                    previousState.copy(plantType = currentAction.newPlantType)
                )
            }
            is CreatePostAction.SelectPlantLightLevel -> {
                previousState.copy(plantLightLevel = currentAction.newLightLevel)
            }
            is CreatePostAction.SelectPlantWaterLevel -> {
                previousState.copy(plantWaterLevel = currentAction.newWaterLevel)
            }
            is CreatePostAction.SelectPlantGrowLevel -> {
                previousState.copy(plantGrowLevel = currentAction.newGrowLevel)
            }
            is CreatePostAction.SwitchPlantIsUserFriendly -> {
                previousState.copy(plantIsUserFriendly = !previousState.plantIsUserFriendly)
            }
            is CreatePostAction.SwitchPlantIsVariegation -> {
                previousState.copy(plantIsVariegation = !previousState.plantIsVariegation)
            }
            is CreatePostAction.EditUnitaryPrice -> {
                val newPrice = if (currentAction.char == ".") {
                    if (previousState.plantUnitaryPrice.contains(".") || previousState.plantUnitaryPrice.isEmpty()) {
                        previousState.plantUnitaryPrice
                    } else {
                        previousState.plantUnitaryPrice + currentAction.char
                    }
                } else {
                    if (currentAction.char == "0" && previousState.plantUnitaryPrice.isEmpty()) {
                        previousState.plantUnitaryPrice
                    } else {
                        if (previousState.plantUnitaryPrice.count() <= 3 || previousState.plantUnitaryPrice[previousState.plantUnitaryPrice.count() - 3].toString() != ".") {
                            previousState.plantUnitaryPrice + currentAction.char
                        } else {
                            previousState.plantUnitaryPrice
                        }
                    }
                }
                canContinueState(previousState.copy(plantUnitaryPrice = newPrice))
            }
            is CreatePostAction.DeleteUnitaryPrice -> {
                canContinueState(previousState.copy(plantUnitaryPrice = previousState.plantUnitaryPrice.dropLast(1)))
            }
        }
    }

    private fun canContinueState(previousState: CreatePostViewState): CreatePostViewState {
        return when (previousState.currentStep) {
            0 -> {
                previousState.copy(
                    canContinue = previousState.postTitle.trim()
                        .isNotEmpty() && previousState.postDescription.trim()
                        //.isNotEmpty() && previousState.plantType.trim()
                        .isNotEmpty() && previousState.filePaths.size > 0
                )
            }
            1 -> {
                previousState.copy(
                    canContinue = previousState.plantUnitaryPrice.trim().isNotEmpty()
                )
            }
            else -> {
                previousState.copy(canContinue = false)
            }
        }
    }

}