package com.youppix.trandingmovies.data.remote

import com.youppix.trandingmovies.data.remote.dto.MovieDetailsResponse
import com.youppix.trandingmovies.data.remote.dto.MoviesResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @Headers(
        "Content-type: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1NGY4ZGNkODViNzE0ZmE5YjFlZmFhY2Y3MjlhNWRiNSIsInN1YiI6IjY2NGI5MzViYTBmNzE0NGU0NDkyZDdkZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rN7VYsWducHzF-PHdBd5S74JK6I05WQfnNIEJJmo93k"
    )
    @GET("discover/movie?include_adult=false&include_video=false&language=en-US&sort_by=popularity.desc")
    suspend fun getTrendingMovies(
            @Query("page") page : Int
    ): Response<MoviesResponse>


    @Headers(
        "Content-type: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1NGY4ZGNkODViNzE0ZmE5YjFlZmFhY2Y3MjlhNWRiNSIsInN1YiI6IjY2NGI5MzViYTBmNzE0NGU0NDkyZDdkZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rN7VYsWducHzF-PHdBd5S74JK6I05WQfnNIEJJmo93k"
    )
    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US"
    ) : Response<MovieDetailsResponse>



}