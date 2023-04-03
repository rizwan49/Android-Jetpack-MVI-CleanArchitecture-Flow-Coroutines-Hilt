package com.rizwan.cinehub.ui.movies

sealed class MoviesListSideEffects {
    class DataNotFound(message: String = "No matches found") : MoviesListSideEffects()
}