package com.rizwan.cinehub.ui.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.rizwan.cinehub.ui.movies.compose.MovieListScreen
import com.rizwan.cinehub.ui.movies.compose.StatusBarUI
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MoviesListActivity : ComponentActivity() {

    @Inject
    lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            StatusBarUI()
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Black
            ) {
                Column {
                    MovieListScreen(
                        viewModel = viewModel,
                        loadNextPage = viewModel::loadNextPage,
                        retry = viewModel::retry,
                    )
                }
            }
        }
    }
}



