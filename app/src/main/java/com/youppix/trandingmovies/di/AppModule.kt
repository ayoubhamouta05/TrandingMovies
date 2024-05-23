package com.youppix.trandingmovies.di

import com.youppix.trandingmovies.data.remote.MoviesApi
import com.youppix.trandingmovies.data.repository.MoviesRepositoryImpl
import com.youppix.trandingmovies.domain.repository.MoviesRepository
import com.youppix.trandingmovies.domain.usecases.GetDetailsMovieUseCase
import com.youppix.trandingmovies.domain.usecases.GetTrendingMoviesUseCase
import com.youppix.trandingmovies.domain.usecases.MoviesUseCases
import com.youppix.trandingmovies.util.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun moviesApi (): MoviesApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MoviesApi::class.java)
    }


     @Provides
     @Singleton
     fun provideMoviesRepository (moviesApi: MoviesApi) : MoviesRepository = MoviesRepositoryImpl(moviesApi)


    @Provides
    @Singleton
    fun provideMoviesUseCases (moviesRepository: MoviesRepository) : MoviesUseCases{
        return MoviesUseCases(
            GetTrendingMoviesUseCase(moviesRepository),
            GetDetailsMovieUseCase(moviesRepository)
        )
    }

}