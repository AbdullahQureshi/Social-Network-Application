package com.globalrescue.data.network.request

import com.globalrescue.data.cache.entity.FavouritesEntity
import com.globalrescue.data.cache.entity.PostEntity
import com.globalrescue.data.entity.PostModel

data class FavouritesRequest(
    val favouriteList: List<PostEntity>
)