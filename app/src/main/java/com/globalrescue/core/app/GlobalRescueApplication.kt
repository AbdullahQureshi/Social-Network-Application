package com.globalrescue.core

import android.app.Application
import androidx.work.*
import com.globalrescue.data.network.workmanager.FavouritesWorkManager
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit


@HiltAndroidApp
class GlobalRescueApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val periodicWorkRequest = PeriodicWorkRequest.Builder(FavouritesWorkManager::class.java, 15, TimeUnit.MINUTES)
            .addTag("FavouritesWorkManager").setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            ).build()

        WorkManager.getInstance(applicationContext)
            .enqueueUniquePeriodicWork("FavouritesWorkManager", ExistingPeriodicWorkPolicy.KEEP, periodicWorkRequest);
    }

}