package com.globalrescue.core.di

import com.globalrescue.core.utils.ConnectivityUtil
import com.globalrescue.data.cache.PostDataStore
import com.globalrescue.data.cache.dao.PostDao
import com.globalrescue.data.network.PostRemoteStore
import com.globalrescue.data.network.api.PostApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped


@Module
@InstallIn(ActivityRetainedComponent::class)
class StoreModule {

    @Provides
    @ActivityRetainedScoped
    fun providesPostDataStore(
        postDao: PostDao,
    ): PostDataStore {
        return PostDataStore(postDao)
    }


    @Provides
    @ActivityRetainedScoped
    fun providesPostRemoteStore(
        postApi: PostApi,
        connectivityUtil: ConnectivityUtil
    ): PostRemoteStore {
        return PostRemoteStore(postApi, connectivityUtil)
    }


}