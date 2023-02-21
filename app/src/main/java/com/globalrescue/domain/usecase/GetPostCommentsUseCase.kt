package com.globalrescue.domain.usecase

import com.globalrescue.R
import com.globalrescue.domain.ProgressBarState
import com.globalrescue.domain.Resource
import com.globalrescue.domain.UIComponent
import com.globalrescue.domain.repo.PostRepo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPostCommentsUseCase @Inject constructor(
    private val postRepo: PostRepo
) {

    operator fun invoke(id: Int) = flow {

        try {
            emit(Resource.Loading(ProgressBarState.Loading))

            emit(Resource.Success(postRepo.getFavouritePosts(id)))

        } catch (exception: Exception) {

            emit(Resource.Error(UIComponent.SnackBar(R.string.error_message)))

        } finally {
            emit(Resource.Loading(ProgressBarState.Idle))

        }
    }
}