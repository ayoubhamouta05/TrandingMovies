package com.youppix.trandingmovies.domain.repository


import com.youppix.trandingmovies.data.remote.dto.MovieDetailsResponse
import com.youppix.trandingmovies.data.remote.dto.MoviesResponse
import retrofit2.Response

interface MoviesRepository {

   suspend fun getTrendingMovies (page :Int) : Response<MoviesResponse>


   suspend fun getMovieById(movieId : Int) : Response<MovieDetailsResponse>

}