package com.globalrescue.domain.repo

import com.globalrescue.data.entity.PostCommentsModel
import com.globalrescue.data.entity.PostModel
import com.globalrescue.data.network.entity.PostCommentsResponse


interface PostRepo {

    suspend fun getPosts(): List<PostModel>
    suspend fun getPostComment(id : Int): List<PostCommentsModel>

}