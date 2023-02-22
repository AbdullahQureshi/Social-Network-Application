package com.globalrescue.data.network.api

import com.globalrescue.data.network.request.FavouritesRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface FavouritesApi {

    @POST("favourites")
    fun postFavourite(@Body favouriteRequest: FavouritesRequest): Boolean

}