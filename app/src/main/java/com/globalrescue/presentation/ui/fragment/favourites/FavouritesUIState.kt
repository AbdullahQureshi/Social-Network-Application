package com.globalrescue.presentation.ui.fragment.favourites

import com.globalrescue.data.entity.PostModel
import com.globalrescue.domain.ProgressBarState
import com.globalrescue.domain.UIComponent

sealed class FavouritesUIState {
    data class FavouritesFetched(val postList: List<PostModel>) : FavouritesUIState()
    data class isFavouritesFetched(val boolean: Boolean) : FavouritesUIState()
    data class FavouritesSetSuccess(val boolean: Boolean) : FavouritesUIState()
    data class Loading(val progressBarState: ProgressBarState = ProgressBarState.Idle) : FavouritesUIState()
    data class Error(val uiComponent: UIComponent) : FavouritesUIState()
    object Nothing : FavouritesUIState()
}
