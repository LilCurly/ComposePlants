package com.example.soplant.presentation.ui.create_post

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.soplant.R
import com.example.soplant.presentation.commons.bottom_sheet.AddImageMethodBottomSheetContent
import com.example.soplant.presentation.commons.bottom_sheet.EditImageMethodBottomSheetContent
import com.example.soplant.presentation.commons.decorators.BottomSheetDecorator
import com.example.soplant.presentation.commons.decorators.PermissionsRequiredDecorator
import com.example.soplant.presentation.theme.Black
import com.example.soplant.presentation.theme.GreenAlpha
import com.example.soplant.presentation.theme.Grey
import com.example.soplant.presentation.theme.White
import com.example.soplant.presentation.ui.components.BaseButtonComponent
import com.example.soplant.presentation.ui.components.NumberPicker
import com.example.soplant.presentation.ui.components.ProcessProgressComponent
import com.example.soplant.presentation.ui.components.TopBarComponent
import com.example.soplant.presentation.ui.create_post.components.AddImagesComponent
import com.example.soplant.presentation.ui.custom.*
import com.example.soplant.presentation.ui.extensions.advancedShadow
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
            Column(modifier = Modifier.fillMaxSize()) {
                TopBarComponent(
                    navController = navController,
                    title = "Create Post",
                    scrollState = scrollState
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    LazyColumn(
                        state = scrollState,
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(start = 26.dp, end = 26.dp, bottom = 40.dp)
                    ) {
                        item {
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
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                val testStateO = remember { mutableStateOf(10) }
                                val testStateT = remember { mutableStateOf(0) }
                                Text(
                                    text = "Price",
                                    style = MaterialTheme.typography.subtitle1,
                                    color = MaterialTheme.colors.primary
                                )
                                Spacer(modifier = Modifier.width(15.dp))
                                Card(
                                    backgroundColor = White,
                                    shape = MaterialTheme.shapes.large,
                                    elevation = 0.dp,
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(58.dp)
                                        .advancedShadow(
                                            color = Grey,
                                            alpha = 0.12f,
                                            shadowBlurRadius = 20.dp,
                                            cornersRadius = 15.dp
                                        )
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Row(
                                            modifier = Modifier.weight(4f),
                                            verticalAlignment = Alignment.Bottom,
                                            horizontalArrangement = Arrangement.End
                                        ) {
                                            NumberPicker(
                                                state = testStateO,
                                                minValue = 1,
                                                maxValue = 9999,
                                                onStateChanged = { testStateO.value = it.toInt() },
                                                textStyle = MaterialTheme.typography.h1.copy(
                                                    color = MaterialTheme.colors.primary,
                                                    textAlign = TextAlign.End,
                                                    fontSize = 20.sp
                                                ),
                                                size = 70.dp,
                                                format = {
                                                    var value = it
                                                    if (value.isEmpty()) {
                                                        value = "0"
                                                    }
                                                    value
                                                },
                                                maxLength = 4
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text(
                                                text = ",",
                                                style = MaterialTheme.typography.subtitle1,
                                                color = Black,
                                                modifier = Modifier.padding(bottom = 6.dp)
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                            NumberPicker(
                                                state = testStateT,
                                                minValue = 0,
                                                maxValue = 99,
                                                onStateChanged = { testStateT.value = it.toInt() },
                                                textStyle = MaterialTheme.typography.h1.copy(
                                                    color = MaterialTheme.colors.primary,
                                                    textAlign = TextAlign.Start,
                                                    fontSize = 20.sp
                                                ),
                                                size = 40.dp,
                                                format = {
                                                    val value = it.padStart(2, '0')
                                                    value
                                                },
                                                maxLength = 2
                                            )
                                        }
                                        Card(
                                            modifier = Modifier
                                                .weight(1f)
                                                .fillMaxHeight()
                                                .advancedShadow(
                                                    color = Grey,
                                                    alpha = 0.1f,
                                                    shadowBlurRadius = 15.dp,
                                                    cornersRadius = 15.dp
                                                ),
                                            elevation = 0.dp,
                                            shape = RoundedCornerShape(0.dp, 15.dp, 15.dp, 0.dp),
                                            backgroundColor = GreenAlpha
                                        ) {
                                            Row(
                                                modifier = Modifier.fillMaxSize(),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.Center
                                            ) {
                                                Text(
                                                    text = "€",
                                                    style = MaterialTheme.typography.h3,
                                                    color = MaterialTheme.colors.primary
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                            KeyboardSpacer()
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(26.dp)
                            .align(Alignment.BottomCenter)
                    ) {
                        BaseButtonComponent(text = "Continue", modifier = Modifier.fillMaxWidth()) {

                        }
                    }
                }
            }
        }
    }
}