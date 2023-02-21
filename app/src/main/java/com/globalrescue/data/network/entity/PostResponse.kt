package com.globalrescue.data.network.entity

import com.globalrescue.data.entity.PostModel
import com.google.gson.annotations.SerializedName

data class PostResponse(

    @SerializedName("userId") val userId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String,

    )

fun PostResponse.toData() = PostModel(
    id = id,
    userId = userId,
    title = title,
    body = body,
    favourite = false
)