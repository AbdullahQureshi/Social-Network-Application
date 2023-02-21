package com.globalrescue.data.cache

import com.globalrescue.data.cache.dao.PostDao
import com.globalrescue.data.cache.entity.toData
import com.globalrescue.data.entity.PostModel
import com.globalrescue.data.entity.toLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class PostDataStore(
    private val postDao: PostDao,
) {


    suspend fun getPosts(): List<PostModel> = withContext(Dispatchers.IO)
    {
        postDao.getAllPosts().map { it.toData() }
    }


    suspend fun savePosts(posts: List<PostModel>): List<PostModel> = withContext(Dispatchers.IO)
    {
        postDao.deleteAll()
        postDao.insertAll(posts.map { it.toLocal() })
        postDao.getAllPosts().map { it.toData() }
    }

    suspend fun getFavouritesPosts(): List<PostModel> = withContext(Dispatchers.IO)
    {
        postDao.getFavouritesPost().map { it.toData() }
    }


    suspend fun setFavourites(id: Int, isFavourite: Boolean) {
        postDao.setFavourite(id, isFavourite)
    }

}