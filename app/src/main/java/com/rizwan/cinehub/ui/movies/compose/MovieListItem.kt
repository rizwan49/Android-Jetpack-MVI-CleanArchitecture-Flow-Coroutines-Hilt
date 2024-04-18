package com.rizwan.cinehub.ui.movies.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.rizwan.cinehub.Utils
import com.rizwan.cinehub.domain.entities.MovieContent
import com.rizwan.cinehub.ui.theme.cineHubTypography

/**
 * This UI will show Movie's banner and name
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListItem(movie: MovieContent, numColumns: Int) {
    Column(horizontalAlignment = Alignment.Start) {
        Card(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .fillMaxWidth()
        ) {
            val mContext = LocalContext.current

            val screenWidth = LocalConfiguration.current.screenWidthDp.dp
            val posterWidth = remember { screenWidth / numColumns - (16.dp / numColumns) }
            val posterHeight = remember { posterWidth * 1.3f }

            Image(
                painter = rememberAsyncImagePainter(
                    model = Utils.getResourceId(bannerName = movie.posterImage, mContext)
                ),
                contentDescription = movie.name,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(posterWidth)
                    .height(posterHeight)
            )
        }
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        Text(
            text = movie.name,
            style = cineHubTypography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp),
            fontWeight = FontWeight.SemiBold,
            color = Color.White.copy(alpha = 0.9f)
        )
        Spacer(modifier = Modifier.height(28.dp))
    }
}