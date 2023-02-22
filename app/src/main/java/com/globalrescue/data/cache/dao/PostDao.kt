package com.globalrescue.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.globalrescue.data.cache.entity.PostEntity


@Dao
interface PostDao {

    @Query("Select * from posts")
    fun getAllPosts(): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: List<PostEntity>)

    @Query("Delete from posts")
    fun deleteAll()



}