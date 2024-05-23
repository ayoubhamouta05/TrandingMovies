package com.youppix.trandingmovies.presentation.fragments.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youppix.trandingmovies.data.remote.dto.MovieDetailsResponse
import com.youppix.trandingmovies.domain.usecases.MoviesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class DetailsMovieViewModel @Inject constructor(
    private val moviesUseCases: MoviesUseCases
) : ViewModel() {

    private val _movieDetails = MutableLiveData<MovieDetailsResponse>()
    val movieDetails : LiveData<MovieDetailsResponse> = _movieDetails

    fun getMovieDetails(movieId : Int) = viewModelScope.launch {
        try {
            val response = moviesUseCases.getDetailsMovieUseCase(movieId)
            if (response.isSuccessful){
                response.body()?.let {
                    _movieDetails.postValue(it)
                }?: run {
                    Log.d("MovieDetailsViewModel", "Response body is null")
                }
            } else {
                Log.d("MovieDetailsViewModel", "Request failed with code: ${response.code()}")
                Log.d("MovieDetailsViewModel", "Error body: ${response.errorBody()?.string()}")
            }
        } catch (e: HttpException) {
            Log.d("MovieDetailsViewModel", "HttpException: ${e.message()}")
        } catch (e: Exception) {
            Log.d("MovieDetailsViewModel", "Exception: ${e.message}")
        }


    }


}