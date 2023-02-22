package com.globalrescue.data.cache

import com.globalrescue.data.cache.dao.FavouritesDao
import com.globalrescue.data.cache.entity.FavouritesEntity
import com.globalrescue.data.cache.entity.toData
import com.globalrescue.data.entity.PostModel


class FavouritesDataStore(
    private val favouritesDao: FavouritesDao,
) {
    fun setFavourites(favouritesEntity: FavouritesEntity) {
        return favouritesDao.setFavourites(favouritesEntity)
    }

    fun getFavouritePosts(): List<PostModel> {
        return favouritesDao.getFavouritesPost().map { it.toData() }
    }


    fun isFavourite(postId: Int): Boolean {
        return favouritesDao.isFavourites(postId)
    }

    fun deleteFavourite(postId: Int) {
        return favouritesDao.deleteFavourites(postId)
    }
}