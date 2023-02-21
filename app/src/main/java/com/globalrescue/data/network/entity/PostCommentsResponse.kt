package com.globalrescue.data.network.entity

import com.globalrescue.data.entity.PostCommentsModel
import com.google.gson.annotations.SerializedName

data class PostCommentsResponse(
    @SerializedName("postId") val postId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("body") val body: String,
)

fun PostCommentsResponse.toData() = PostCommentsModel(
    postId = postId,
    id = id,
    name = name,
    email = email,
    body = body
)
