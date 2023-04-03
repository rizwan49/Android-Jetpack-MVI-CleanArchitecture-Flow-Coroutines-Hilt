package com.rizwan.cinehub.domain.di

import com.rizwan.cinehub.data.repository.MoviesRepository
import com.rizwan.cinehub.data.repository.MoviesRepositoryImpl
import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun bindMoviesRepository(moviesRepositoryImpl: MoviesRepositoryImpl): MoviesRepository

}






