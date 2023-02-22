package com.globalrescue.data.network

import android.accounts.NetworkErrorException
import com.globalrescue.core.utils.ConnectivityUtil
import com.globalrescue.data.entity.PostCommentsModel
import com.globalrescue.data.entity.PostModel
import com.globalrescue.data.network.api.PostApi
import com.globalrescue.data.network.entity.toData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRemoteStore(
    private val postApi: PostApi,
    private val connectivity: ConnectivityUtil
) {

    suspend fun getPosts(): List<PostModel> = withContext(Dispatchers.IO) {

        if (connectivity.isInternetAvailable()) {
            postApi.getPosts().let {
                it.map { it.toData() }
            }
        } else {
            arrayListOf()
        }

    }


    suspend fun getPostComments(id: Int): List<PostCommentsModel> = withContext(Dispatchers.IO) {

        if (connectivity.isInternetAvailable()) {
            postApi.getPostComments(id).map { it.toData() }
        } else {
            throw NetworkErrorException()
        }

    }

}