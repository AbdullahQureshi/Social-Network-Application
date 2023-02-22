package com.globalrescue.presentation.ui.fragment.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.globalrescue.data.cache.entity.FavouritesEntity
import com.globalrescue.domain.Resource
import com.globalrescue.domain.interactor.FavouritesInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val favouritesInteractor: FavouritesInteractor
) : ViewModel() {


    private val _uiState = MutableLiveData<FavouritesUIState>(FavouritesUIState.Nothing)
    val uiState: LiveData<FavouritesUIState> get() = _uiState

    fun onEvent(event: FavouritesEvent) {
        when (event) {
            is FavouritesEvent.GetFavourites -> {
                getFavouritesPosts()
            }

            is FavouritesEvent.setFavourites -> {

                setFavourites(event.postId, event.isFavourite)
            }

            is FavouritesEvent.isFavourites -> {

                isFavourite(event.postId)
            }


        }
    }

    private fun isFavourite(postId: Int) = viewModelScope.launch {


        favouritesInteractor.isFavouritesUseCase.invoke(postId).onEach { result ->


            when (result) {

                is Resource.Success -> {
                    _uiState.value = FavouritesUIState.isFavouritesFetched(result.data!!)

                }

                is Resource.Error -> {
                    _uiState.value = FavouritesUIState.Error(result.uiComponent)
                }

                is Resource.Loading -> {
                    _uiState.value = FavouritesUIState.Loading(result.progressBarState)

                }

            }


        }.launchIn(this)


    }


    private fun setFavourites(postId: Int, isFavourites: Boolean) = viewModelScope.launch {

        val favouritesEntity = FavouritesEntity(postId = postId)

        if (isFavourites) {
            favouritesInteractor.setFavouritesUseCase.invoke(favouritesEntity).onEach { result ->


                when (result) {

                    is Resource.Success -> {
                        _uiState.value = FavouritesUIState.FavouritesSetSuccess(true)

                    }

                    is Resource.Error -> {
                        _uiState.value = FavouritesUIState.Error(result.uiComponent)
                    }

                    is Resource.Loading -> {
                        _uiState.value = FavouritesUIState.Loading(result.progressBarState)

                    }

                }


            }.launchIn(this)

        } else {

            favouritesInteractor.deleteFavouritesUseCase.invoke(postId).onEach { result ->


                when (result) {

                    is Resource.Success -> {
                        _uiState.value = FavouritesUIState.FavouritesSetSuccess(false)

                    }

                    is Resource.Error -> {
                        _uiState.value = FavouritesUIState.Error(result.uiComponent)
                    }

                    is Resource.Loading -> {
                        _uiState.value = FavouritesUIState.Loading(result.progressBarState)

                    }

                }


            }.launchIn(this)
        }


    }


    private fun getFavouritesPosts() = viewModelScope.launch {

        favouritesInteractor.getFavouritesUseCase.invoke().onEach { result ->

            when (result) {

                is Resource.Success -> {
                    result.data?.let {
                        _uiState.value = FavouritesUIState.FavouritesFetched(it)
                    }
                }

                is Resource.Error -> {
                    _uiState.value = FavouritesUIState.Error(result.uiComponent)
                }

                is Resource.Loading -> {
                    _uiState.value = FavouritesUIState.Loading(result.progressBarState)

                }

            }


        }.launchIn(this)

    }


}