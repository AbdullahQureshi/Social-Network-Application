package com.globalrescue.data.entity

import com.globalrescue.data.cache.entity.PostEntity

data class PostModel(
    val userId: Int = 0,
    val id: Int = 0,
    val title: String,
    val body: String,
    val favourite: Int
)

fun PostModel.toDomain() = PostModel(
    id = id,
    userId = userId,
    title = title,
    body = body,
    favourite = 0
)

fun PostModel.toLocal() = PostEntity(
    id = id,
    userId = userId,
    title = title,
    body = body,
    favourite = favourite
)