package com.rizwan.cinehub.domain.usecases


import android.util.Log
import com.rizwan.cinehub.data.repository.MoviesRepositoryImpl
import com.rizwan.cinehub.data.source.local.Content
import com.rizwan.cinehub.data.source.local.LocalMovieModel
import com.rizwan.cinehub.domain.entities.MovieContent
import com.rizwan.cinehub.domain.entities.MoviesEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * this use-case will return the list of movies from @see[MoviesRepositoryImpl]
 */

class GetMoviesListUseCase @Inject constructor(
    private val moviesRepository: MoviesRepositoryImpl
) {

    suspend fun getMoviesList(page: Int): Flow<com.rizwan.cinehub.domain.Result> = flow {
        try {
            Log.d("Rizwan", "getMoviesList called from GetMoviesListUseCase")
            val entity = moviesRepository.getMoviesList(page = page).toEntity()
            emit(com.rizwan.cinehub.domain.Result.Success(entity))
        } catch (e: Exception) {
            Log.d("Rizwan", "GetMoviesListUseCase error ")
            emit(com.rizwan.cinehub.domain.Result.Error(e.message ?: "Unknown error"))
        }
    }

    private fun LocalMovieModel.toEntity() = MoviesEntity(
        pageNum = this.page.pageNum,
        title = this.page.title,
        contentList = this.page.contentItems.content.map {
            it.toMovieContent()
        }
    )
}

fun Content.toMovieContent() = MovieContent(
    name = this.name,
    posterImage = this.posterImage
)




