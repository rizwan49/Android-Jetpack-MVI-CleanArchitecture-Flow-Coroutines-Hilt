package com.rizwan.cinehub.ui.movies


import com.rizwan.cinehub.domain.entities.MovieContent


data class MoviesState(
    val status: UiStatus? = UiStatus.LOADING,
    val page: Int = 0,
    val pageNum: String? = null,
    val title: String? = null,
    val contentList: MutableList<MovieContent> = mutableListOf()
)

enum class UiStatus {
    LOADING, LOADED, ERROR
}