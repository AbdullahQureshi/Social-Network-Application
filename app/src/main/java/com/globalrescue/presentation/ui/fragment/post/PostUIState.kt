package com.globalrescue.presentation.ui.fragment.post

import com.globalrescue.data.entity.PostCommentsModel
import com.globalrescue.data.entity.PostModel
import com.globalrescue.domain.ProgressBarState
import com.globalrescue.domain.UIComponent

sealed class PostUIState
{
    data class PostsFetched(val postList: List<PostModel>) : PostUIState()
    data class PostCommentsFetched(val postCommentsList: List<PostCommentsModel>) : PostUIState()
    data class Loading(val progressBarState: ProgressBarState = ProgressBarState.Idle) : PostUIState()
    data class Error(val uiComponent: UIComponent) : PostUIState()
    object Nothing : PostUIState()
}
