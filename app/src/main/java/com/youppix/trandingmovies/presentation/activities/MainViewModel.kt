package com.youppix.trandingmovies.presentation.activities

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youppix.trandingmovies.data.remote.dto.MoviesResponse
import com.youppix.trandingmovies.domain.usecases.MoviesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
private val moviesUseCases: MoviesUseCases
) : ViewModel() {


    private var _trendingMovies = MutableLiveData<MoviesResponse>()
    val trendingMovies: LiveData<MoviesResponse> = _trendingMovies

    private var _page = MutableLiveData<Int>(1)

    init {
        getMovies(page = _page.value!!)
    }

    fun getMovies(page : Int) = viewModelScope.launch {
        try {
            val response = moviesUseCases.getTrendingMoviesUseCase(page)
            if (response.isSuccessful) {
                response.body()?.let {
                    _trendingMovies.postValue(it)

                } ?: run {
                    Log.d("TrendingMovieViewModel", "Response body is null")
                }
            } else {
                Log.d("TrendingMovieViewModel", "Request failed with code: ${response.code()}")
                Log.d("TrendingMovieViewModel", "Error body: ${response.errorBody()?.string()}")
            }
        } catch (e: HttpException) {
            Log.d("TrendingMovieViewModel", "HttpException: ${e.message()}")
        } catch (e: Exception) {
            Log.d("TrendingMovieViewModel", "Exception: ${e.message}")
        }
    }

    fun toNextPage() {
        _page.value = _page.value!! + 1
        clearTrendingMovies()
        getMovies(_page.value!!)
    }

    fun toPreviousPage() {
        if (_page.value!! > 1) {
            _page.value = _page.value!! - 1
            clearTrendingMovies()
            getMovies(_page.value!!)
        }
    }

    private fun clearTrendingMovies() {
        _trendingMovies.value = MoviesResponse(page =0,results = emptyList(), total_results = 0,
            total_pages = 0
        )
    }


}