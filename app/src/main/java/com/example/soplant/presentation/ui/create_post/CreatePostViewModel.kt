package com.example.soplant.presentation.ui.create_post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soplant.redux.Store
import com.example.soplant.redux.create_post.CreatePostAction
import com.example.soplant.redux.create_post.CreatePostViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(private val store: Store<CreatePostViewState, CreatePostAction>) :
    ViewModel() {
    val state: StateFlow<CreatePostViewState> = store.state

    fun addFilePath(filePath: String) {
        val action = CreatePostAction.AddFilePath(filePath)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun addMultipleFilePaths(filePaths: List<String>) {
        val action = CreatePostAction.AddMultipleFilePaths(filePaths)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun editFilePath(filePath: String) {
        val action = CreatePostAction.EditFilePath(filePath)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun askPermission(permission: String) {
        val action = CreatePostAction.AskPermission(permission)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun openAddImageBottomSheet() {
        val action = CreatePostAction.OpenAddImageBottomSheet

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun openEditImageBottomSheet() {
        val action = CreatePostAction.OpenEditImageBottomSheet

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun closeBottomSheet() {
        val action = CreatePostAction.CloseBottomSheet

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun clearPermission() {
        val action = CreatePostAction.ClearPermission

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }

    fun tapImage(index: Int) {
        val action = CreatePostAction.TappedImage(index)

        viewModelScope.launch {
            store.dispatch(action, this)
        }
    }
}