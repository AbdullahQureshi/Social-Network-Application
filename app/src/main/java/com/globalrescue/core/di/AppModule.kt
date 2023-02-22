package com.globalrescue.core.di

import android.content.Context
import com.globalrescue.core.utils.ConnectivityUtil
import com.globalrescue.data.network.api.FavouritesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideConnectivityUtl(): ConnectivityUtil {
        return ConnectivityUtil()
    }


    @Provides
    @Singleton
    fun providesFavouritesApi(@ApiClient retrofit: Retrofit): FavouritesApi {
        return retrofit.create(FavouritesApi::class.java)
    }
}