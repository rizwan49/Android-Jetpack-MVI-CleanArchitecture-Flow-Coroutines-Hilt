package com.rizwan.cinehub.domain.di

import com.rizwan.cinehub.data.repository.MoviesRepositoryImpl
import com.rizwan.cinehub.domain.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindMoviesRepository(
        moviesRepositoryImpl: MoviesRepositoryImpl
    ): MoviesRepository

}

//@Module
//@InstallIn(FragmentComponent::class)
//object MoviesViewModelModule {
//
//    @Provides
//    fun provideMoviesViewModel(
//        fragment : Fragment,
//        factory : ViewModelProvider.Factory
//    ) : MoviesViewModel {
//        return ViewModelProvider(
//            fragment,
//            factory
//        )[MoviesViewModel::class.java]
//    }
//
//}






