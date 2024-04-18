package com.rizwan.cinehub.domain.usecases


import android.util.Log
import com.rizwan.cinehub.data.repository.MoviesRepositoryImpl
import com.rizwan.cinehub.data.source.local.Content
import com.rizwan.cinehub.data.source.local.LocalMovieModel
import com.rizwan.cinehub.domain.MoviesRepository
import com.rizwan.cinehub.domain.Result
import com.rizwan.cinehub.domain.di.IoDispatcher
import com.rizwan.cinehub.domain.entities.MovieContent
import com.rizwan.cinehub.domain.entities.MoviesEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * this use-case will return the list of movies from @see[MoviesRepositoryImpl]
 */

class GetMoviesListUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    @IoDispatcher val dispatcher: CoroutineDispatcher,
) {

    suspend fun getMoviesList(page: Int): Flow<Result<MoviesEntity>> = flow {
        try {
            Log.d("Rizwan", "getMoviesList called from GetMoviesListUseCase")
            val entity = moviesRepository.getMoviesList(page = page).toEntity()
            emit(Result.Success(entity))
        } catch (e: Exception) {
            Log.d("Rizwan", "GetMoviesListUseCase error ")
            emit(Result.Error(e.message ?: "Unknown error"))
        }
    }.flowOn(dispatcher)

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




