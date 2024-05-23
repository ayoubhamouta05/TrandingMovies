package com.youppix.trandingmovies.presentation.fragments.trending

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.youppix.trandingmovies.data.remote.dto.MoviesResponse
import com.youppix.trandingmovies.domain.usecases.MoviesUseCases
import com.youppix.trandingmovies.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class TrendingMoviesViewModel @Inject constructor(
    private val moviesUseCases: MoviesUseCases, application: Application
) : AndroidViewModel(application) {

    private var _trendingMovies = MutableLiveData<MoviesResponse>()
    val trendingMovies: LiveData<MoviesResponse> = _trendingMovies

    private var _page = MutableLiveData<Int>(1)

    val loading = MutableLiveData(false)

    fun getMovies(page : Int) {
        moviesUseCases.getTrendingMoviesUseCase(page).onEach { result->
            when(result){
                is Resource.Loading -> {
                    loading.postValue(true)
                }
                is Resource.Successful -> {
                    result.data?.let {
                        _trendingMovies.postValue(it)
                    }
                }
                is Resource.Error -> {
                    result.message?.let {
                        Log.d("MovieDetailsViewModel", "${result.message}")
                    } ?: {
                        Log.d("MovieDetailsViewModel", "An unexpected error occurred")
                    }
                }
            }

        }.launchIn(viewModelScope)
    }

    fun toNextPage() {
        loading.postValue(true)
        _page.value = _page.value!! + 1
        getMovies(_page.value!!)
    }

    fun toPreviousPage() {
        if (_page.value!! > 1) {
            loading.postValue(true)
            _page.value = _page.value!! - 1
            getMovies(_page.value!!)
        }
    }


}