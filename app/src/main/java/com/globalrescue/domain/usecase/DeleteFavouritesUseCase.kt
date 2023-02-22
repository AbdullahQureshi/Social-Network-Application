package com.globalrescue.domain.usecase

import com.globalrescue.data.cache.entity.FavouritesEntity
import com.globalrescue.domain.ProgressBarState
import com.globalrescue.domain.Resource
import com.globalrescue.domain.repo.FavouritesRepo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteFavouritesUseCase @Inject constructor(
    private val favouritesRepo: FavouritesRepo
) {

    operator fun invoke(postId: Int) = flow {

        try {
            emit(Resource.Loading(ProgressBarState.Loading))

            emit(Resource.Success(favouritesRepo.deleteFavourites(postId)))

        } catch (exception: Exception) {

        } finally {
            emit(Resource.Loading(ProgressBarState.Idle))

        }
    }
}