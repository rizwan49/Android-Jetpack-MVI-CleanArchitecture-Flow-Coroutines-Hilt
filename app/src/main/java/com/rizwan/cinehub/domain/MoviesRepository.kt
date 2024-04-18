package com.rizwan.cinehub.domain

import com.rizwan.cinehub.data.source.local.Content
import com.rizwan.cinehub.data.source.local.LocalMovieModel

interface MoviesRepository {

    suspend fun getMoviesList(page: Int): LocalMovieModel

    suspend fun getSearchedMoviesList(searchedText: String): List<Content>

}
