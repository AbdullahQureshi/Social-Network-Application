package com.globalrescue.data.cache

import com.globalrescue.data.cache.dao.PostDao
import com.globalrescue.data.cache.entity.toData
import com.globalrescue.data.entity.PostModel
import com.globalrescue.data.entity.toLocal


class PostDataStore(
    private val postDao: PostDao,
) {


    fun getPosts(): List<PostModel> {
        return postDao.getAllPosts().map { it.toData() }
    }


    fun savePosts(posts: List<PostModel>): List<PostModel> {
        postDao.deleteAll()
        postDao.insertAll(posts.map { it.toLocal() })
        return postDao.getAllPosts().map { it.toData() }
    }


}