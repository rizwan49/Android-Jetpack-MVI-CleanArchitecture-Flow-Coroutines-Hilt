package com.rizwan.cinehub.ui.movies.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.rizwan.cinehub.R
import com.rizwan.cinehub.ui.theme.cineHubTypography

/**
 * SearchBar UI
 */
@Composable
fun SearchBarUI(title: String) {
    Box(
        modifier = Modifier
            .padding(top = 24.dp, start = 12.dp, end = 12.dp)
            .fillMaxWidth()
            .height(56.dp)
    ) {
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

        Image(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(36.dp)
                .padding(8.dp)
                .clickable {
                    // need to add click action
                },
            painter = rememberAsyncImagePainter(model = R.drawable.search),
            contentDescription = "back button",
        )
    }
}
