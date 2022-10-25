package com.example.soplant.presentation.commons.decorators

import androidx.compose.runtime.*
import com.example.soplant.presentation.commons.popup.PermissionRequestPopupContent
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionsRequiredDecorator(
    permission: String,
    rationalePopupContent: String,
    onPermissionGranted: (String) -> Unit,
    onPermissionRefused: (String) -> Unit,
    nextComposable: @Composable () -> Unit
) {
    var shouldShow by remember {
        mutableStateOf(false)
    }

    val permissionState =
        rememberPermissionState(permission = permission, onPermissionResult = { isGranted ->
            if (isGranted) {
                onPermissionGranted(permission)
            } else {
                onPermissionRefused(permission)
            }
        })

    PopupDecorator(shouldShow = shouldShow, onClose = { shouldShow = false }, content = {
        PermissionRequestPopupContent(
            content = rationalePopupContent,
        ) {
            shouldShow = false
            permissionState.launchPermissionRequest()
        }
    }) {
        nextComposable()

        if (permission.isNotEmpty()) {
            LaunchedEffect(key1 = permission, block = {
                when (permissionState.status) {
                    PermissionStatus.Granted -> {
                        onPermissionGranted(permission)
                    }
                    is PermissionStatus.Denied -> {
                        if (permissionState.status.shouldShowRationale) {
                            shouldShow = true
                        } else {
                            permissionState.launchPermissionRequest()
                        }
                    }
                }

            })
        }
    }
}