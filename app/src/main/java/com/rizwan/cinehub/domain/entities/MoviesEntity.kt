package com.rizwan.cinehub.domain.entities

import androidx.compose.runtime.Stable

/**
 * Entity class, as it's required to achieve MVI + Clean architecture,
 * from use-case will hold the only required data which will be useful for maintain the state and update the UI accordingly.
 */
@Stable
data class MoviesEntity(
    val pageNum: String,
    val title: String,
    val contentList: List<MovieContent> = mutableListOf()
)

data class MovieContent(
    val name: String,
    val posterImage: String
)
