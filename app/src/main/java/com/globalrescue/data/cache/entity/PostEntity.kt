package com.globalrescue.data.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.globalrescue.data.entity.PostModel


@Entity(tableName = "Posts")
data class PostEntity(
    @PrimaryKey val id: Int = 0,
    @ColumnInfo(name = "userId") val userId: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "body") val body: String,
    @ColumnInfo(name = "favourite") val favourite: Boolean
)

fun PostEntity.toData() = PostModel(
    id = id,
    userId = userId,
    title = title,
    body = body,
    favourite = favourite
)