package com.globalrescue.data.entity

import com.globalrescue.data.cache.entity.PostEntity

data class PostModel(
    val userId: Int = 0,
    val id: Int = 0,
    val title: String,
    val body: String,
    val favourite: Boolean
)

fun PostModel.toDomain() = PostModel(
    id = id,
    userId = userId,
    title = title,
    body = body,
    favourite = false
)

fun PostModel.toLocal() = PostEntity(
    id = id,
    userId = userId,
    title = title,
    body = body,
    favourite = favourite
)