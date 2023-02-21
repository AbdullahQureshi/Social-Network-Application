package com.globalrescue.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GlobalRescueApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}