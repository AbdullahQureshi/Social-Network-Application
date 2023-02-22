package com.globalrescue.domain.repo

import com.globalrescue.data.cache.entity.FavouritesEntity
import com.globalrescue.data.entity.PostModel


interface FavouritesRepo {

    suspend fun getFavouritePosts(): List<PostModel>
    suspend fun setFavourite(favouritesEntity: FavouritesEntity)
    suspend fun isFavourite(postId: Int) : Boolean
    suspend fun deleteFavourites(postId: Int)

}