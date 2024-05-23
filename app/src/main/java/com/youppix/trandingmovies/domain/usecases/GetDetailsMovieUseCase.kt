package com.youppix.trandingmovies.domain.usecases

import com.youppix.trandingmovies.data.remote.dto.MovieDetailsResponse
import com.youppix.trandingmovies.domain.repository.MoviesRepository
import retrofit2.Response

class GetDetailsMovieUseCase(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke(movieId: Int): Response<MovieDetailsResponse> {
        return moviesRepository.getMovieById(movieId)
    }

}