package com.globalrescue.domain.interactor

import com.globalrescue.domain.usecase.DeleteFavouritesUseCase
import com.globalrescue.domain.usecase.GetFavouritesUseCase
import com.globalrescue.domain.usecase.SetFavouritesUseCase
import com.globalrescue.domain.usecase.isFavouritesUseCase

data class FavouritesInteractor(val getFavouritesUseCase: GetFavouritesUseCase, val setFavouritesUseCase: SetFavouritesUseCase,val isFavouritesUseCase: isFavouritesUseCase, val deleteFavouritesUseCase: DeleteFavouritesUseCase)