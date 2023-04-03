package com.rizwan.cinehub.domain.usecases

import com.rizwan.cinehub.data.repository.MoviesRepositoryImpl
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepositoryImpl
) {

}