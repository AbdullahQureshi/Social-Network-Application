package com.globalrescue.data.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.globalrescue.data.entity.PostModel


@Entity(tableName = "Favourites")
data class FavouritesEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "postId") val postId: Int = 0,
)
