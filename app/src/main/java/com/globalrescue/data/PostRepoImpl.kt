package com.globalrescue.data

import com.globalrescue.data.cache.PostDataStore
import com.globalrescue.data.entity.PostCommentsModel
import com.globalrescue.data.entity.PostModel
import com.globalrescue.data.entity.toDomain
import com.globalrescue.data.network.PostRemoteStore
import com.globalrescue.domain.repo.PostRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostRepoImpl @Inject constructor(
    private val postDataStore: PostDataStore,
    private val postRemoteStore: PostRemoteStore
) : PostRepo {
    override suspend fun getPosts(): List<PostModel> = withContext(Dispatchers.IO) {

        val postsFromRemote = postRemoteStore.getPosts()
        if (postsFromRemote.isNotEmpty()) {
            postDataStore.savePosts(postsFromRemote).map { it.toDomain() }
        } else {
            getPostsFromLocal()
        }
    }


    override suspend fun getPostComment(id: Int): List<PostCommentsModel> = withContext(Dispatchers.IO) {
        postRemoteStore.getPostComments(id)
    }

    override suspend fun getFavouritePosts(): List<PostModel> {
        TODO("Not yet implemented")
    }

    override suspend fun setFavourite(id: Long, isFavourite: Boolean) {
        TODO("Not yet implemented")
    }


    private suspend fun getPostsFromLocal(): List<PostModel> = withContext(Dispatchers.IO) {
        postDataStore.getPosts().map {
            it.toDomain()
        }
    }

}