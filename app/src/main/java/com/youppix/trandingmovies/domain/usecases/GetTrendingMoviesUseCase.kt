package com.youppix.trandingmovies.domain.usecases

import com.youppix.trandingmovies.data.remote.dto.MoviesResponse
import com.youppix.trandingmovies.domain.repository.MoviesRepository
import com.youppix.trandingmovies.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetTrendingMoviesUseCase(
    private val moviesRepository: MoviesRepository
) {

    operator fun invoke(page : Int): Flow<Resource<MoviesResponse>> = flow{
        try {
            emit(Resource.Loading())
            val movies =moviesRepository.getTrendingMovies(page)
            emit(Resource.Successful(movies))

        }catch (e : HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }catch (e : IOException){
            emit(Resource.Error("Couldn't reach server ,Check your Internet Connection"))
        }
    }


}