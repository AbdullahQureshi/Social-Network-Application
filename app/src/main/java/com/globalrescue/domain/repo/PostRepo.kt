package com.globalrescue.domain.repo

import com.globalrescue.data.entity.PostCommentsModel
import com.globalrescue.data.entity.PostModel
import com.globalrescue.data.network.entity.PostCommentsResponse


interface PostRepo {

    suspend fun getPosts(): List<PostModel>
    suspend fun getFavouritePosts(id : Int): List<PostCommentsModel>
    suspend fun setFavourite(id: Long, isFavourite: Boolean)

}