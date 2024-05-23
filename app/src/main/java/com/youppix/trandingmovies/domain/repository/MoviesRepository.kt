package com.youppix.trandingmovies.domain.repository


import com.youppix.trandingmovies.data.remote.dto.MovieDetailsResponse
import com.youppix.trandingmovies.data.remote.dto.MoviesResponse

interface MoviesRepository {

   suspend fun getTrendingMovies (page :Int) : MoviesResponse


   suspend fun getMovieById(movieId : Int) : MovieDetailsResponse

}