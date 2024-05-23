package com.youppix.trandingmovies.domain.usecases

import com.youppix.trandingmovies.data.remote.dto.MovieDetailsResponse
import com.youppix.trandingmovies.domain.repository.MoviesRepository
import com.youppix.trandingmovies.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetDetailsMovieUseCase(
    private val moviesRepository: MoviesRepository
) {

    operator fun invoke(movieId: Int): Flow<Resource<MovieDetailsResponse>> = flow{
        try {
            emit(Resource.Loading())
            val details =moviesRepository.getMovieById(movieId = movieId)
            emit(Resource.Successful(details))

        }catch (e : HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }catch (e : IOException){
            emit(Resource.Error("Couldn't reach server ,Check your Internet Connection"))
        }

    }

}