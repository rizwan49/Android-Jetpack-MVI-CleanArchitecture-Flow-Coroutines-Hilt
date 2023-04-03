package com.rizwan.cinehub.ui.movies.compose

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.rizwan.cinehub.R
import com.rizwan.cinehub.Utils
import com.rizwan.cinehub.ui.movies.MoviesListSideEffects
import com.rizwan.cinehub.ui.movies.MoviesViewModel
import com.rizwan.cinehub.ui.movies.UiStatus
import org.orbitmvi.orbit.compose.collectSideEffect

/**
 * Main screen with list of movies
 *
 * @param viewModel: @see[MoviesViewModel]
 * @param loadNextPage: lambda method required to by pass action to viewModel to load next page movie's data
 * which will pass
 * @param retry : in-case exception occur, this will retry to loadNextPage
 */
@Composable
fun MovieListScreen(
    viewModel: MoviesViewModel,
    loadNextPage: () -> Unit,
    retry: () -> Unit
) {

    val state by viewModel.container.stateFlow.collectAsState()
    val mContext = LocalContext.current

    viewModel.collectSideEffect {
        handleSideEffect(
            context = mContext,
            sideEffect = it
        )
    }

    val isFetching by remember {
        derivedStateOf {
            state.status == UiStatus.LOADING
        }
    }

    when (state.status) {
        UiStatus.LOADING -> {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .background(color = Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(48.dp))
            }
        }

        UiStatus.LOADED -> {
            val movies = state
            val orientation = LocalConfiguration.current.orientation

            val scrollState = rememberLazyGridState()

            SearchBarUI(title = movies.title ?: "")

            val spanCount = when (orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> 5
                else -> 3
            }

            movies?.let {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(spanCount),

                    contentPadding = PaddingValues(
                        start = 12.dp,
                        top = 16.dp,
                        end = 12.dp,
                        bottom = 16.dp
                    ),
                    modifier = Modifier.fillMaxSize(),
                    state = scrollState,
                    content = {
                        items(movies.contentList.size) { index ->
                            MovieListItem(movie = movies.contentList[index], spanCount)
                            if (!isFetching && index == (movies.contentList.size - 1)) {
                                loadNextPage()
                            }
                        }
                    },
                    userScrollEnabled = true,
                )
            }

        }
        UiStatus.ERROR -> {
            retry()
        }
        else -> {
            //do nothing
        }
    }
}

/**
 * method to handle the side effects
 */
fun handleSideEffect(context: Context, sideEffect: MoviesListSideEffects) {
    when (sideEffect) {
        is MoviesListSideEffects.DataNotFound -> Utils.showToast(
            context = context,
            message = "No data found"
        )
    }
}



