package com.globalrescue.data

import com.globalrescue.data.cache.FavouritesDataStore
import com.globalrescue.data.cache.entity.FavouritesEntity
import com.globalrescue.data.entity.PostModel
import com.globalrescue.domain.repo.FavouritesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavouritesRepoImpl @Inject constructor(
    private val favouritesDataStore: FavouritesDataStore,
) : FavouritesRepo {
    override suspend fun getFavouritePosts(): List<PostModel> = withContext(Dispatchers.IO) {
        favouritesDataStore.getFavouritePosts()
    }

    override suspend fun setFavourite(favouritesEntity: FavouritesEntity) = withContext(Dispatchers.IO) {

        favouritesDataStore.setFavourites(favouritesEntity)

    }

    override suspend fun isFavourite(postId: Int): Boolean = withContext(Dispatchers.IO) {

        favouritesDataStore.isFavourite(postId)

    }

    override suspend fun deleteFavourites(postId: Int) = withContext(Dispatchers.IO) {

        favouritesDataStore.deleteFavourite(postId)

    }


}