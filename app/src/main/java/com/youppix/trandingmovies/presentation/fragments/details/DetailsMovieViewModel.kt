package com.youppix.trandingmovies.presentation.fragments.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youppix.trandingmovies.data.remote.dto.MovieDetailsResponse
import com.youppix.trandingmovies.domain.usecases.MoviesUseCases
import com.youppix.trandingmovies.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailsMovieViewModel @Inject constructor(
    private val moviesUseCases: MoviesUseCases
) : ViewModel() {

    private val _movieDetails = MutableLiveData<MovieDetailsResponse>()
    val movieDetails: LiveData<MovieDetailsResponse> = _movieDetails

    val loading = MutableLiveData<Boolean>(false)
    private val _error = MutableLiveData("")
    val error : LiveData<String> = _error

    fun getMovieDetails(movieId: Int) {


        moviesUseCases.getDetailsMovieUseCase(movieId).onEach { result ->

            when (result) {
                is Resource.Successful -> {
                    result.data?.let {
                        _movieDetails.postValue(it)
                        _error.postValue(null)
                    }
                }

                is Resource.Loading -> {
                    loading.postValue(true)
                }

                is Resource.Error -> {
                    result.message?.let {
                        _error.postValue(result.message)
                        Log.d("MovieDetailsViewModel", "${result.message}")
                    } ?: {
                        _error.postValue("An unexpected error occurred")
                        Log.d("MovieDetailsViewModel", "An unexpected error occurred")
                    }

                }

            }

        }.launchIn(viewModelScope)
    }

}


