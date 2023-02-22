package com.globalrescue.domain.usecase

import com.globalrescue.R
import com.globalrescue.domain.ProgressBarState
import com.globalrescue.domain.Resource
import com.globalrescue.domain.UIComponent
import com.globalrescue.domain.repo.FavouritesRepo
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavouritesUseCase @Inject constructor(
    private val favouritesRepo: FavouritesRepo
) {

    operator fun invoke() = flow {

        try {
            emit(Resource.Loading(ProgressBarState.Loading))

            emit(Resource.Success(favouritesRepo.getFavouritePosts()))

        } catch (exception: Exception) {

            emit(Resource.Error(UIComponent.SnackBar(R.string.empty_error_message)))

        } finally {
            emit(Resource.Loading(ProgressBarState.Idle))

        }
    }
}