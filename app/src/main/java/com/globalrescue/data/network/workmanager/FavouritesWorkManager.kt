package com.globalrescue.data.network.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.globalrescue.data.cache.dao.FavouritesDao
import com.globalrescue.data.network.api.FavouritesApi
import com.globalrescue.data.network.request.FavouritesRequest
import javax.inject.Inject

class FavouritesWorkManager(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {


    @Inject
    lateinit var favouritesApi: FavouritesApi

    @Inject
    lateinit var favouritesDao: FavouritesDao

    override fun doWork(): Result {

        val favouritesRequest = FavouritesRequest(favouritesDao.getFavouritesPost())
        favouritesApi.postFavourite(favouritesRequest)
        return Result.success()
    }

}