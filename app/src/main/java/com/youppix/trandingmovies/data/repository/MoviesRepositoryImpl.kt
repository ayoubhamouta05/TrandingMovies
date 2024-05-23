package com.youppix.trandingmovies.data.repository

import com.youppix.trandingmovies.data.remote.MoviesApi
import com.youppix.trandingmovies.data.remote.dto.MovieDetailsResponse
import com.youppix.trandingmovies.data.remote.dto.MoviesResponse
import com.youppix.trandingmovies.domain.repository.MoviesRepository

class MoviesRepositoryImpl(
    private val moviesApi :MoviesApi
)  : MoviesRepository{
    override suspend fun getTrendingMovies(page : Int): MoviesResponse = moviesApi.getTrendingMovies(page)


    override suspend fun getMovieById(movieId : Int): MovieDetailsResponse = moviesApi.getMovieById(movieId)
}