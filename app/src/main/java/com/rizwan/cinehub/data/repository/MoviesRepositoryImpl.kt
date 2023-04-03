package com.rizwan.cinehub.data.repository

import android.util.Log
import com.rizwan.cinehub.data.MoviesManager
import com.rizwan.cinehub.data.source.local.LocalMovieModel
import com.rizwan.cinehub.domain.di.ApplicationScope
import com.rizwan.cinehub.domain.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * This will ask @see[MoviesManager] to return the required information.
 */
class MoviesRepositoryImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    @ApplicationScope private val scope: CoroutineScope,
    private val moviesManager: MoviesManager
) : MoviesRepository {

    companion object {
        const val TAG = "MoviesRepositoryImpl"
    }

    override suspend fun getMoviesList(page: Int): LocalMovieModel {
        return withContext(dispatcher) {
            Log.d(TAG, "getMoviesList get called for page: $page")
            moviesManager.getAllMovies(page = page)
        }
    }

    override suspend fun getSearchedMoviesList(moviesName: String): List<LocalMovieModel> {
        TODO("Not yet implemented")
    }

}