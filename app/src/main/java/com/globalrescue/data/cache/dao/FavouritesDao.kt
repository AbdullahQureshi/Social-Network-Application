package com.globalrescue.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.globalrescue.data.cache.entity.FavouritesEntity
import com.globalrescue.data.cache.entity.PostEntity


@Dao
interface FavouritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setFavourites(favourites: FavouritesEntity)

    @Query("SELECT * FROM posts left join favourites where posts.id = favourites.postId")
    fun getFavouritesPost(): List<PostEntity>


    @Query("DELETE FROM favourites where favourites.postId = :postId")
    fun deleteFavourites(postId: Int)

    @Query("SELECT EXISTS(SELECT * FROM Favourites where postId =:postId)")
    fun isFavourites(postId: Int): Boolean


}