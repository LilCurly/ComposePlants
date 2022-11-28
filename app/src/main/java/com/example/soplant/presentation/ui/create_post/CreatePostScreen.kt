package com.example.soplant.presentation.ui.create_post

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.soplant.R
import com.example.soplant.presentation.commons.bottom_sheet.AddImageMethodBottomSheetContent
import com.example.soplant.presentation.commons.bottom_sheet.EditImageMethodBottomSheetContent
import com.example.soplant.presentation.commons.decorators.BottomSheetDecorator
import com.example.soplant.presentation.commons.decorators.PermissionsRequiredDecorator
import com.example.soplant.presentation.commons.decorators.TopBarDecorator
import com.example.soplant.presentation.ui.components.BaseButtonComponent
import com.example.soplant.presentation.ui.components.ProcessProgressComponent
import com.example.soplant.presentation.ui.create_post.components.PostFormComponent
import com.example.soplant.presentation.ui.create_post.components.PostUnitaryPriceComponent
import com.example.soplant.presentation.ui.custom.KeyboardSpacer
import com.example.soplant.presentation.utils.FileUtils
import com.example.soplant.redux.create_post.CreatePostViewState
import java.io.File

@Composable
fun ComposeCreatePostScreen(
    navController: NavController,
    viewModel: CreatePostViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    val scrollState = rememberLazyListState()
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    var file by remember {
        mutableStateOf<File?>(null)
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { isSuccess ->
            if (isSuccess && file != null) {
                if (state.bottomSheetState == CreatePostViewState.BottomSheetState.ADD_IMAGE) {
                    viewModel.addFilePath(file!!.absolutePath)
                } else if (state.bottomSheetState == CreatePostViewState.BottomSheetState.EDIT_IMAGE) {
                    viewModel.editFilePath(file!!.absolutePath)
                }
            }
            viewModel.closeBottomSheet()
        }
    )
    val galleryMultipleLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = { images ->
            val imagesList = mutableListOf<String>()
            run loop@{
                for (i in 0 until (5 - state.filePaths.size)) {
                    if (i < images.size) {
                        FileUtils.createCopyAndReturnRealPath(context, images[i])?.let {
                            imagesList.add(it)
                        }
                    } else {
                        return@loop
                    }
                }
            }
            if (imagesList.isNotEmpty()) {
                viewModel.addMultipleFilePaths(imagesList)
            }
            viewModel.closeBottomSheet()
        })
    val galleryUniqueLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { image ->
            image?.let {
                FileUtils.createCopyAndReturnRealPath(context, it)?.let { absolutePath ->
                    viewModel.editFilePath(absolutePath)
                }
            }
            viewModel.closeBottomSheet()
        }
    )

    PermissionsRequiredDecorator(
        permission = state.currentPermissionRequest,
        rationalePopupContent = "Your permission is required for us to be able to use your picture.",
        onPermissionGranted = {
            viewModel.clearPermission()
            file = FileUtils.createImageFile(context)
            file?.let {
                cameraLauncher.launch(FileUtils.getUriFromFile(context, it))
            }
        },
        onPermissionRefused = {
            viewModel.clearPermission()
        },
        onPopupClose = {
            viewModel.clearPermission()
        }
    ) {
        BottomSheetDecorator(
            shouldShow = state.bottomSheetState != CreatePostViewState.BottomSheetState.NONE,
            onClose = { viewModel.closeBottomSheet() },
            bottomSheetContent = {
                if (state.bottomSheetState == CreatePostViewState.BottomSheetState.ADD_IMAGE) {
                    AddImageMethodBottomSheetContent(onTakePicture = {
                        viewModel.askPermission(android.Manifest.permission.CAMERA)
                    }, onPickGallery = {
                        galleryMultipleLauncher.launch("image/*")
                    })
                } else if (state.bottomSheetState == CreatePostViewState.BottomSheetState.EDIT_IMAGE) {
                    EditImageMethodBottomSheetContent(onTakePicture = {
                        viewModel.askPermission(android.Manifest.permission.CAMERA)
                    }, onPickGallery = {
                        galleryUniqueLauncher.launch("image/*")
                    })
                }
            }) {
            TopBarDecorator(
                navController = navController,
                title = "Create Post",
                scrollState = scrollState
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        if (state.currentStep == 19) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 26.dp, end = 26.dp, bottom = 92.dp)
                            ) {
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    ProcessProgressComponent(
                                        images = listOf(
                                            R.drawable.icon_bank,
                                            R.drawable.icon_bank,
                                            R.drawable.icon_bank,
                                            R.drawable.icon_bank
                                        ),
                                        currentStep = state.currentStep,
                                        previousStep = state.previousStep,
                                        onClickStep = {
                                            if (it < state.lastHighestStep) {
                                                viewModel.navigateToStep(it)
                                            }
                                        }
                                    )
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        } else {
                            LazyColumn(
                                state = scrollState,
                                modifier = Modifier.fillMaxSize(),
                                contentPadding = PaddingValues(
                                    start = 26.dp,
                                    end = 26.dp,
                                    bottom = 92.dp
                                )
                            ) {
                                item {
                                    Row(modifier = Modifier.fillMaxWidth()) {
                                        ProcessProgressComponent(
                                            images = listOf(
                                                R.drawable.icon_plant,
                                                R.drawable.icon_bank,
                                                R.drawable.icon_box,
                                                R.drawable.icon_bank
                                            ),
                                            currentStep = state.currentStep,
                                            previousStep = state.previousStep,
                                            onClickStep = {
                                                if (it < state.lastHighestStep) {
                                                    viewModel.navigateToStep(it)
                                                }
                                            }
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(16.dp))
                                    when (state.currentStep) {
                                        0 -> {
                                            PostFormComponent(viewModel, state, focusManager)
                                        }
                                        1 -> {
                                            PostUnitaryPriceComponent(viewModel, state)
                                        }
                                    }
                                    KeyboardSpacer()
                                }
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(26.dp)
                                .align(Alignment.BottomCenter)
                        ) {
                            BaseButtonComponent(
                                text = "Continue",
                                enabled = state.canContinue,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                viewModel.navigateToStep(state.currentStep + 1)
                            }
                        }
                    }
                }
            }
        }
    }
}