package com.globalrescue.data.network.api

import com.globalrescue.data.network.entity.PostCommentsResponse
import com.globalrescue.data.network.entity.PostResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApi {

    @GET("posts")
    suspend fun getPosts(): List<PostResponse>

    @GET("posts/{id}/comments")
    suspend fun getPostComments(@Path("id") id: Int): List<PostCommentsResponse>
}