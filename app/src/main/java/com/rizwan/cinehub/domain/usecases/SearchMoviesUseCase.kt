package com.rizwan.cinehub.domain.usecases

import android.util.Log
import com.rizwan.cinehub.data.repository.MoviesRepositoryImpl
import com.rizwan.cinehub.domain.Result
import com.rizwan.cinehub.domain.di.DefaultDispatcher
import com.rizwan.cinehub.domain.entities.MovieContent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    @DefaultDispatcher val dispatcher: CoroutineDispatcher,
    private val moviesRepository: MoviesRepositoryImpl
) {

    suspend fun performSearch(searchedText: String): Flow<Result<List<MovieContent>>> = flow {
        try {
            Log.d("search", "getMoviesList called from SearchMoviesUseCase")
            val entity = moviesRepository.getSearchedMoviesList(searchedText).map {
                it.toMovieContent()
            }.distinct()
            emit(Result.Success(entity))
        } catch (e: Exception) {
            Log.d("search", "SearchMoviesUseCase error ")
            emit(Result.Error(e.message ?: "Unknown error"))
        }
    }.flowOn(dispatcher)
}