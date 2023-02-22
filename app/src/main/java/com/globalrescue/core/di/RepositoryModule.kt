package com.globalrescue.core.di

import com.globalrescue.data.FavouritesRepoImpl
import com.globalrescue.data.PostRepoImpl
import com.globalrescue.domain.repo.FavouritesRepo
import com.globalrescue.domain.repo.PostRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {

    @Binds
    @ActivityRetainedScoped
    abstract fun providePostRepository(postRepoImpl: PostRepoImpl): PostRepo


    @Binds
    @ActivityRetainedScoped
    abstract fun provideFavouritesRepository(favouritesRepoImpl: FavouritesRepoImpl): FavouritesRepo

}