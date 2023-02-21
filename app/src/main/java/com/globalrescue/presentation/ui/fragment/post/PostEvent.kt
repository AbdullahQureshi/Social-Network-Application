package com.globalrescue.presentation.ui.fragment.post

sealed class PostEvent {
    object GetPosts : PostEvent()
    data class GetPostComments(val id: Int) : PostEvent()

}