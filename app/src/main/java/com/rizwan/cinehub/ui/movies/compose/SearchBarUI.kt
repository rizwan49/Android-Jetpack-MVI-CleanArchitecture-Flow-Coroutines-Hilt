package com.rizwan.cinehub.ui.movies.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.rizwan.cinehub.R
import com.rizwan.cinehub.ui.movies.MoviesState
import com.rizwan.cinehub.ui.theme.cineHubTypography

/**
 * SearchBar UI
 */
@Composable
fun SearchBarUI(
    title: String,
    moviesState: MoviesState,
    searchIconClicked: (isEnabled: Boolean) -> Unit,
    backButtonClicked: () -> Unit,
    performSearch: (searchText: String) -> Unit
) {

    Box(
        modifier = Modifier
            .padding(top = 24.dp, start = 12.dp, end = 12.dp)
            .fillMaxWidth()
            .height(56.dp)
    ) {

        AnimatedVisibility(visible = moviesState.isSearchActivated.not()) {
            Row(
                modifier = Modifier
                    .matchParentSize()
                    .align(Alignment.CenterStart),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(36.dp)
                        .padding(8.dp)
                        .clickable {
                            // need to add click action
                        },
                    painter = rememberAsyncImagePainter(model = R.drawable.back),
                    contentDescription = "back button",
                )

                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = title,
                    style = cineHubTypography.titleLarge,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }
        }

        AnimatedVisibility(visible = moviesState.isSearchActivated) {

            var searchText by remember {
                mutableStateOf("")
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                TextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                        performSearch(searchText)
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = androidx.compose.ui.text.input.ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = {
                        performSearch(searchText)
                    }),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White,
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.White,
                        containerColor = Color.Transparent,
                    ),
                    placeholder = {
                        Text(
                            text = "search movie here",
                            style = cineHubTypography.bodyLarge,
                            modifier = Modifier.background(Color.Transparent),
                            color = Color.White.copy(alpha = 0.6f),
                            fontSize = 18.sp

                        )
                    }
                )
            }

        }


        Image(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(36.dp)
                .padding(8.dp)
                .clickable {
                    searchIconClicked(!moviesState.isSearchActivated)
                    // need to add click action
                },
            painter = rememberAsyncImagePainter(
                model = if (moviesState.isSearchActivated.not()) {
                    R.drawable.search
                } else {
                    R.drawable.search_cancel
                }
            ),
            contentDescription = "back button",
        )
    }
}
