package com.globalrescue.domain.interactor

import com.globalrescue.domain.usecase.GetPostCommentsUseCase
import com.globalrescue.domain.usecase.GetPostUseCase

data class PostInteractor(val getPostUseCase: GetPostUseCase , val getPostCommentsUseCase: GetPostCommentsUseCase)