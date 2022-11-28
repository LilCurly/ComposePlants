package com.example.soplant.presentation.ui.create_post.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.unit.dp
import com.example.soplant.R
import com.example.soplant.presentation.ui.create_post.CreatePostViewModel
import com.example.soplant.presentation.ui.custom.*
import com.example.soplant.redux.create_post.CreatePostViewState

@Composable
fun PostFormComponent(
    viewModel: CreatePostViewModel,
    state: CreatePostViewState,
    focusManager: FocusManager
) {
    Row {
        Spacer(modifier = Modifier.width(14.dp))
        Text(
            text = "Add Images",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.primary
        )
    }
    Spacer(modifier = Modifier.height(13.dp))
    AddImagesComponent(
        images = state.filePaths,
        onAddClick = { viewModel.openAddImageBottomSheet() },
        onDeleteClick = { viewModel.deleteFilePath(it) },
        onEditClick = { index ->
            viewModel.tapImage(index)
            viewModel.openEditImageBottomSheet()
        }
    )
    Spacer(modifier = Modifier.height(13.dp))
    CustomTextField(
        value = state.postTitle,
        onValueChange = { viewModel.editPostTitle(it) },
        placeholder = "Enter here",
        title = "Post Title"
    )
    Spacer(modifier = Modifier.height(9.dp))
    CustomDropDown(
        values = listOf(),
        selectedValue = null,
        title = "Plant Type",
        placeholder = "Enter here",
        isLoading = false,
        focusManager = focusManager,
        onSelectChanged = {}
    )
    Spacer(modifier = Modifier.height(9.dp))
    CustomMultiLineTextField(
        value = state.postDescription,
        onValueChange = { viewModel.editPostDescription(it) },
        placeholder = "Enter here",
        title = "Description"
    )
    Spacer(modifier = Modifier.height(9.dp))
    CustomSwitch(
        title = "Is variegation ?",
        currentValue = state.plantIsVariegation
    ) {
        viewModel.switchPlantIsVariegation()
    }
    Spacer(modifier = Modifier.height(9.dp))
    CustomSwitch(
        title = "Is beginner friendly ?",
        currentValue = state.plantIsUserFriendly
    ) {
        viewModel.switchPlantIsUserFriendly()
    }
    Spacer(modifier = Modifier.height(9.dp))
    CustomHorizontalOptions(
        image = R.drawable.icon_bank,
        title = "Light level",
        values = listOf("Low", "Medium", "High"),
        selectedIndex = state.plantLightLevel,
        onValueSelected = {
            viewModel.selectPlantLightLevel(it)
        }
    )
    Spacer(modifier = Modifier.height(9.dp))
    CustomHorizontalOptions(
        image = R.drawable.icon_bank,
        title = "Water level",
        values = listOf("Low", "Medium", "High"),
        selectedIndex = state.plantWaterLevel,
        onValueSelected = {
            viewModel.selectPlantWaterLevel(it)
        }
    )
    Spacer(modifier = Modifier.height(9.dp))
    CustomDropDown(
        values = listOf(),
        selectedValue = null,
        title = "Maturity",
        placeholder = "Enter here",
        isLoading = false,
        focusManager = focusManager,
        onSelectChanged = {}
    )
}