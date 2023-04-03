package com.rizwan.cinehub.data.repository

import com.rizwan.cinehub.data.source.local.LocalMovieModel

interface MoviesRepository {

    suspend fun getMoviesList(page: Int): LocalMovieModel

    suspend fun getSearchedMoviesList(moviesName: String): List<LocalMovieModel>

}
