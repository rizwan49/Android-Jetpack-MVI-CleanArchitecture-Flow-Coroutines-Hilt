package com.rizwan.cinehub.domain.usecases

import android.util.Log
import com.rizwan.cinehub.data.repository.MoviesRepositoryImpl
import com.rizwan.cinehub.domain.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepositoryImpl
) {

    suspend fun performSearch(searchedText: String): Flow<Result> = flow {
        try {
            Log.d("search", "getMoviesList called from SearchMoviesUseCase")
            val entity = moviesRepository.getSearchedMoviesList(searchedText).map {
                it.toMovieContent()
            }.distinct()
            emit(Result.SearchedSuccess(entity))
        } catch (e: Exception) {
            Log.d("search", "SearchMoviesUseCase error ")
            emit(Result.Error(e.message ?: "Unknown error"))
        }
    }
}