package com.youppix.trandingmovies.domain.usecases

import com.youppix.trandingmovies.data.remote.dto.MoviesResponse
import com.youppix.trandingmovies.domain.repository.MoviesRepository
import retrofit2.Response

class GetTrendingMoviesUseCase(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke(page : Int): Response<MoviesResponse> {
        return moviesRepository.getTrendingMovies(page)
    }


}