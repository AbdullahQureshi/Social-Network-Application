package com.globalrescue.core.di

import com.globalrescue.domain.interactor.PostInteractor
import com.globalrescue.domain.usecase.GetPostCommentsUseCase
import com.globalrescue.domain.usecase.GetPostUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object InterActorModule {

    @Provides
    @ActivityRetainedScoped
    fun provideGetPostsUseCases(
        getPostUseCase: GetPostUseCase,
        getPostCommentsUseCase: GetPostCommentsUseCase

    ): PostInteractor {
        return PostInteractor(getPostUseCase, getPostCommentsUseCase)
    }

}