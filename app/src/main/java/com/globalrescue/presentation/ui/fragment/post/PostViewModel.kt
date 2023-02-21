package com.globalrescue.presentation.ui.fragment.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.globalrescue.domain.Resource
import com.globalrescue.domain.interactor.PostInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PostViewModel @Inject constructor(
    private val postInteractor: PostInteractor
) : ViewModel() {


    private val _uiState = MutableLiveData<PostUIState>(PostUIState.Nothing)
    val uiState: LiveData<PostUIState> get() = _uiState

    fun onEvent(event: PostEvent) {
        when (event) {
            is PostEvent.GetPosts -> {
                getPosts()
            }
            is PostEvent.GetPostComments -> {
                getPostComments(event.id)
            }
        }
    }

    private fun getPostComments(id: Int) = viewModelScope.launch {


        postInteractor.getPostCommentsUseCase.invoke(id).onEach { result ->

            when (result) {

                is Resource.Success -> {
                    result.data?.let {
                        _uiState.value = PostUIState.PostCommentsFetched(it)
                    }
                }

                is Resource.Error -> {
                    _uiState.value = PostUIState.Error(result.uiComponent)
                }

                is Resource.Loading -> {
                    _uiState.value = PostUIState.Loading(result.progressBarState)

                }

            }


        }.launchIn(this)

    }

    private fun getPosts() = viewModelScope.launch {

        postInteractor.getPostUseCase.invoke().onEach { result ->

            when (result) {

                is Resource.Success -> {
                    result.data?.let {
                        _uiState.value = PostUIState.PostsFetched(it)
                    }
                }

                is Resource.Error -> {
                    _uiState.value = PostUIState.Error(result.uiComponent)
                }

                is Resource.Loading -> {
                    _uiState.value = PostUIState.Loading(result.progressBarState)

                }

            }


        }.launchIn(this)

    }


}