package com.globalrescue.presentation.ui.fragment.favourites

sealed class FavouritesEvent {
    object GetFavourites : FavouritesEvent()
    data class isFavourites(val postId : Int) : FavouritesEvent()
    data class setFavourites(val postId : Int, val isFavourite: Boolean) : FavouritesEvent()
}