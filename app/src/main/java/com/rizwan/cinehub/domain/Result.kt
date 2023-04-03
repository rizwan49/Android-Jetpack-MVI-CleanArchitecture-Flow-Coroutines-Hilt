package com.rizwan.cinehub.domain

import com.rizwan.cinehub.domain.entities.MovieContent
import com.rizwan.cinehub.domain.entities.MoviesEntity

/**
 * this sealed class will help to identify the process or request status.
 */
sealed class Result {
    data class Success(val movies: MoviesEntity) : Result()

    data class SearchedSuccess(val content: List<MovieContent>) : Result()
    data class Error(val errorMessage: String) : Result()
}